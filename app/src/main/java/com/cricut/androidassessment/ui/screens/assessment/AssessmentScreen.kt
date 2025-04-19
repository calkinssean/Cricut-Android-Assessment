package com.cricut.androidassessment.ui.screens.assessment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.ui.common.LoadingScreen
import com.cricut.androidassessment.ui.common.composables.AssessmentButton
import com.cricut.androidassessment.ui.common.composables.AssessmentTopBar
import com.cricut.androidassessment.ui.screens.assessment.composables.QuestionContent
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID

const val AssessmentScreenRoute = "AssessmentScreenRoute"

fun NavGraphBuilder.assessmentScreen() {
    composable(
        route = AssessmentScreenRoute
    ) { backStackEntry ->

        val viewModel: AssessmentViewModel = hiltViewModel()
        val uiState by viewModel.observableModel.collectAsStateWithLifecycle()

        AssessmentScreen(
            modifier = Modifier.fillMaxSize(),
            state = uiState
        )
    }
}

@Composable
private fun AssessmentScreen(
    modifier: Modifier = Modifier,
    state: AssessmentScreenState
) {
    when {
        state.isLoading -> LoadingScreen(modifier = modifier)
        else -> AssessmentScreenContent(
            modifier = modifier.padding(horizontal = 16.dp),
            state = state
        )
    }
}

@Composable
private fun AssessmentScreenContent(
    modifier: Modifier,
    state: AssessmentScreenState
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AssessmentTopBar(modifier = Modifier.fillMaxWidth(), title = "Assessment")
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(bottom = 16.dp)
            ) {

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(),
                    progress = {
                        state.progress
                    }
                )

                val currentQuestion = state.currentQuestion
                if (currentQuestion != null) {
                    QuestionContent(
                        modifier = Modifier
                            .weight(1f),
                        question = currentQuestion
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                AssessmentButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    enabled = state.isCurrentQuestionAnswered,
                    text = if (state.isLastQuestion) "Submit" else "Next"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssessmentScreen() {
    val state = AssessmentScreenState(
        isLoading = false,
        questions = listOf(
            TrueFalseQuestion(
                questionId = UUID.randomUUID().toString(),
                questionText = "Question 1",
                questionType = QuestionType.TrueFalse,
                correctAnswer = true
            ),
            TrueFalseQuestion(
                questionId = UUID.randomUUID().toString(),
                questionText = "Question 2",
                questionType = QuestionType.TrueFalse,
                correctAnswer = true
            )
        ),
        currentQuestionIndex = 1
    )
    AndroidAssessmentTheme {
        AssessmentScreen(
            modifier = Modifier.fillMaxSize(),
            state = state
        )
    }
}
