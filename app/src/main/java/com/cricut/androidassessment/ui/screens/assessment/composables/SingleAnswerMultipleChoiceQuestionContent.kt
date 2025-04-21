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
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.ui.common.composables.SelectableRow
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID


@Composable
fun SingleAnswerMultipleChoiceQuestionContent(
    modifier: Modifier = Modifier,
    question: SingleAnswerMultipleChoiceQuestion,
    answer: MultipleChoiceAnswer?,
    onValueChanged: (Question, Any) -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(question.questionText, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(50.dp))
        question.options.forEachIndexed { index, it ->
            SelectableRow(
                text = it,
                checked = answer?.selectedIndices?.contains(index) ?: false,
                onClick = { onValueChanged(question, index) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleAnswerMultipleChoiceQuestionContentPreview() {
    AndroidAssessmentTheme {
        val question = SingleAnswerMultipleChoiceQuestion(
            id = UUID.randomUUID().toString(),
            questionText = "What is your favorite color?",
            options = listOf("Red", "Green", "Blue", "Yellow", "Orange"),
            correctAnswerIndex = 2
        )
        val answer = MultipleChoiceAnswer(
            questionId = UUID.randomUUID().toString(),
            questionType = question.questionType,
            selectedIndices = setOf(2)
        )
        SingleAnswerMultipleChoiceQuestionContent(
            modifier = Modifier.fillMaxSize(),
            question = question,
            answer = answer,
            onValueChanged = { _, _ -> }
        )
    }
}