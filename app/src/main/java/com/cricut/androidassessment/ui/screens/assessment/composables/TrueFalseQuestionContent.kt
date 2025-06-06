package com.cricut.androidassessment.ui.screens.assessment.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.ui.common.composables.SelectableRow
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID

@Composable
fun TrueFalseQuestionContent(
    modifier: Modifier,
    question: TrueFalseQuestion,
    answer: TrueFalseAnswer?,
    onValueChanged: (Question, Any) -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(question.questionText, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(50.dp))
        SelectableRow(
            text = "True",
            checked = answer?.answer == true,
            onClick = { onValueChanged(question, true) }
        )
        SelectableRow(
            text = "False",
            checked = answer?.answer == false,
            onClick = { onValueChanged(question, false) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TrueFalseQuestionContentPreview() {
    AndroidAssessmentTheme {
        val question = TrueFalseQuestion(
            id = UUID.randomUUID().toString(),
            questionText = "Do you want to hire Sean?",
            questionType = QuestionType.TrueFalse,
            correctAnswer = true
        )
        val answer = TrueFalseAnswer(
            questionId = question.id,
            questionType = QuestionType.TrueFalse,
            answer = true
        )
        TrueFalseQuestionContent(
            modifier = Modifier.fillMaxWidth(),
            question = question,
            answer = answer,
            onValueChanged = { _, _ -> }
        )
    }
}