package com.cricut.androidassessment.ui.screens.assessment.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.ui.common.composables.SelectableRow
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID


@Composable
fun SingleAnswerMultipleChoiceQuestionContent(
    modifier: Modifier = Modifier,
    question: SingleAnswerMultipleChoiceQuestion
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(question.questionText)
        Spacer(modifier = Modifier.height(50.dp))
        question.options.forEach {
            SelectableRow(text = it, checked = true, onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleAnswerMultipleChoiceQuestionContentPreview() {
    AndroidAssessmentTheme {
        val question = SingleAnswerMultipleChoiceQuestion(
            questionId = UUID.randomUUID().toString(),
            questionText = "What is your favorite color?",
            options = listOf("Red", "Green", "Blue", "Yellow", "Orange"),
            correctAnswerIndex = 2
        )
        SingleAnswerMultipleChoiceQuestionContent(
            modifier = Modifier.fillMaxSize(),
            question = question
        )
    }
}