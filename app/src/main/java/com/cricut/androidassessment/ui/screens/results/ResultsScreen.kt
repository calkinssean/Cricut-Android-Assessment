package com.cricut.androidassessment.ui.screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cricut.androidassessment.data.model.results.AssessmentResult
import com.cricut.androidassessment.data.model.results.AssessmentResults
import com.cricut.androidassessment.ui.common.LoadingScreen
import com.cricut.androidassessment.ui.common.composables.AssessmentButton
import com.cricut.androidassessment.ui.common.composables.AssessmentTopBar
import com.cricut.androidassessment.ui.screens.common.AssessmentViewModel
import com.cricut.androidassessment.ui.screens.results.composables.ResultListItem
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

const val ResultsScreenRoute = "ResultsScreenRoute"

@Composable
fun ResultsScreen(
    modifier: Modifier = Modifier,
    viewModel: AssessmentViewModel,
    onRetryClicked: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getAssessmentResults()
    }
    val state by viewModel.observableModel.collectAsStateWithLifecycle()
    val results = state.results
    when {
        state.fetchingResults || results == null -> LoadingScreen(modifier = modifier)
        else -> ResultsScreenContent(
            modifier = modifier,
            results = results,
            onRetryClicked = onRetryClicked
        )
    }
}

@Composable
private fun ResultsScreenContent(
    modifier: Modifier,
    results: AssessmentResults,
    onRetryClicked: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AssessmentTopBar(modifier = Modifier.fillMaxWidth(), title = "Results")
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "You scored %.1f%%".format(results.score * 100),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                results.results.forEach {
                    ResultListItem(modifier = Modifier.fillMaxWidth(), it)
                }
                Spacer(modifier = Modifier.weight(1f))
                AssessmentButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Retry",
                    onClick = {
                        onRetryClicked()
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun ResultsScreenPreview() {
    AndroidAssessmentTheme {
        val results = AssessmentResults(
            score = 0.875f,
            results = listOf(
                AssessmentResult(
                    question = "What is the answer to this question?",
                    correctAnswer = "42",
                    answer = "I don't know",
                    isCorrect = false
                ),
                AssessmentResult(
                    question = "What is the answer to this question?",
                    correctAnswer = "42",
                    answer = "42",
                    isCorrect = true
                )
            )
        )
        ResultsScreenContent(
            modifier = Modifier.fillMaxSize(),
            results = results,
            onRetryClicked = {}
        )
    }
}