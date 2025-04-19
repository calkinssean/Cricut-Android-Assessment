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
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.ui.common.composables.SelectableRow
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID

@Composable
fun MultiAnswerMultipleChoiceQuestionContent(
    modifier: Modifier = Modifier,
    question: MultiAnswerMultipleChoiceQuestion
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(question.questionText)
        Text("Select all that apply")
        Spacer(modifier = Modifier.height(50.dp))
        question.options.forEach {
            SelectableRow(text = it, checked = true, onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultipleChoiceQuestionContentPreview() {
    AndroidAssessmentTheme {
        val question = MultiAnswerMultipleChoiceQuestion(
            questionId = UUID.randomUUID().toString(),
            questionText = "Which of the following are primary colors?",
            options = listOf("Red", "Green", "Blue", "Yellow", "Orange"),
            correctAnswers = setOf(0, 2, 3)
        )
        MultiAnswerMultipleChoiceQuestionContent(
            modifier = Modifier.fillMaxSize(),
            question = question
        )
    }
}