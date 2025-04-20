package com.cricut.androidassessment.ui.screens.assessment.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.data.model.answer.MultipleChoiceAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.ui.common.composables.SelectableRow
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID

@Composable
fun MultiAnswerMultipleChoiceQuestionContent(
    modifier: Modifier = Modifier,
    question: MultiAnswerMultipleChoiceQuestion,
    answer: MultipleChoiceAnswer? = null,
    onValueChanged: (Question, Any) -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(question.questionText, style = MaterialTheme.typography.titleMedium)
        Text("Select all that apply", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(50.dp))
        question.options.forEachIndexed { index, text ->
            SelectableRow(
                text = text,
                checked = answer?.selectedIndices?.contains(index) ?: false,
                onClick = {
                    onValueChanged(question, index)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiAnswerMultipleChoiceQuestionContentPreview() {
    AndroidAssessmentTheme {
        val question = MultiAnswerMultipleChoiceQuestion(
            id = UUID.randomUUID().toString(),
            questionText = "Which of the following are primary colors?",
            options = listOf("Red", "Green", "Blue", "Yellow", "Orange"),
            correctAnswers = setOf(0, 2, 3)
        )
        val answer = MultipleChoiceAnswer(
            questionId = question.id,
            questionText = question.questionText,
            selectedIndices = setOf(0, 2, 3),
            questionType = QuestionType.MultiAnswerMultipleChoice
        )
        MultiAnswerMultipleChoiceQuestionContent(
            modifier = Modifier.fillMaxSize(),
            question = question,
            answer = answer,
            onValueChanged = { _, _ -> }
        )
    }
}