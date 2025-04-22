package com.cricut.androidassessment.ui.screens.assessment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.ui.common.LoadingScreen
import com.cricut.androidassessment.ui.common.composables.AssessmentButton
import com.cricut.androidassessment.ui.common.composables.AssessmentTopBar
import com.cricut.androidassessment.ui.screens.assessment.composables.QuestionContent
import com.cricut.androidassessment.ui.screens.common.AssessmentState
import com.cricut.androidassessment.ui.screens.common.AssessmentViewModel
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID

const val AssessmentScreenRoute = "AssessmentScreenRoute"

private data class AssessmentScreenInteractions(
    val onNextClicked: () -> Unit,
    val onPreviousQuestionClicked: () -> Unit,
    val onAnswerValueChanged: (Question, Any) -> Unit,
    val onAssessmentComplete: () -> Unit
) {
    companion object {
        val Empty = AssessmentScreenInteractions(
            onNextClicked = {},
            onPreviousQuestionClicked = {},
            onAnswerValueChanged = { _, _ -> },
            onAssessmentComplete = {}
        )
    }
}

@Composable
fun AssessmentScreen(
    modifier: Modifier = Modifier,
    viewModel: AssessmentViewModel,
    onAssessmentComplete: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.fetchQuestions()
    }

    val state by viewModel.observableModel.collectAsStateWithLifecycle()

    val interactions = AssessmentScreenInteractions(
        onNextClicked = viewModel::onNextClicked,
        onPreviousQuestionClicked = viewModel::onPreviousQuestionClicked,
        onAnswerValueChanged = viewModel::onAnswerValueChanged,
        onAssessmentComplete = onAssessmentComplete
    )

    when {
        state.isLoading -> LoadingScreen(modifier = modifier)
        else -> AssessmentScreenContent(
            modifier = modifier.padding(horizontal = 16.dp),
            state = state,
            interactions = interactions
        )
    }
}

@Composable
private fun AssessmentScreenContent(
    modifier: Modifier,
    state: AssessmentState,
    interactions: AssessmentScreenInteractions
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
                        modifier = Modifier.weight(1f),
                        question = currentQuestion,
                        answer = state.currentAnswer,
                        onValueChanged = interactions.onAnswerValueChanged
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
                if (state.currentQuestionIndex != 0) {
                    AssessmentButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        onClick = { interactions.onPreviousQuestionClicked() },
                        text = "Previous Question"
                    )
                }
                AssessmentButton(
                    modifier = Modifier.fillMaxWidth(),
                    isBusy = state.isBusy,
                    onClick = {
                        if (state.isLastQuestion)
                            interactions.onAssessmentComplete() else
                            interactions.onNextClicked()
                    },
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
    val state = AssessmentState(
        isLoading = false,
        questions = listOf(
            TrueFalseQuestion(
                id = UUID.randomUUID().toString(),
                questionText = "Question 1",
                questionType = QuestionType.TrueFalse,
                correctAnswer = true
            ),
            TrueFalseQuestion(
                id = UUID.randomUUID().toString(),
                questionText = "Question 2",
                questionType = QuestionType.TrueFalse,
                correctAnswer = true
            ),
            MultiAnswerMultipleChoiceQuestion(
                id = UUID.randomUUID().toString(),
                questionText = "Which of the following are primary colors?",
                options = listOf("Red", "Green", "Blue", "Yellow", "Orange"),
                correctAnswers = setOf(0, 2, 3)
            )
        ),
        currentQuestionIndex = 2
    )
    AndroidAssessmentTheme {
        AssessmentScreenContent(
            modifier = Modifier.fillMaxSize(),
            state = state,
            interactions = AssessmentScreenInteractions.Empty
        )
    }
}
