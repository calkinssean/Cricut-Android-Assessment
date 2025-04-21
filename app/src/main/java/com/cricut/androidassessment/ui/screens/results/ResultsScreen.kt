package com.cricut.androidassessment.ui.screens.results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cricut.androidassessment.ui.common.LoadingScreen
import com.cricut.androidassessment.ui.common.composables.AssessmentTopBar
import com.cricut.androidassessment.ui.screens.common.AssessmentState
import com.cricut.androidassessment.ui.screens.assessment.AssessmentViewModel
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

const val ResultsScreenRoute = "ResultsScreenRoute"

@Composable
fun ResultsScreen(
    modifier: Modifier = Modifier,
    viewModel: AssessmentViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.getAssessmentResults()
    }
    val state by viewModel.observableModel.collectAsStateWithLifecycle()
    when {
        state.fetchingResults -> LoadingScreen(modifier = modifier)
        else -> ResultsScreenContent(modifier = modifier, state = state)
    }
}

@Composable
private fun ResultsScreenContent(
    modifier: Modifier,
    state: AssessmentState
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AssessmentTopBar(modifier = Modifier.fillMaxWidth(), title = "Results")
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {

            }
        }
    )
}

@Preview
@Composable
private fun ResultsScreenPreview() {
    AndroidAssessmentTheme {
        val state = AssessmentState(
            isLoading = false,
            fetchingResults = true
        )
        ResultsScreenContent(
            modifier = Modifier.fillMaxSize(),
            state = state
        )
    }
}