package com.cricut.androidassessment.ui.screens.assessment.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.TextInputQuestion
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import java.util.UUID

@Composable
fun TextInputQuestionContent(modifier: Modifier, question: TextInputQuestion) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(question.questionText, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(value = "", modifier = Modifier.fillMaxWidth(), onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun TextInputQuestionContentPreview() {
    AndroidAssessmentTheme {
        val question = TextInputQuestion(
            id = UUID.randomUUID().toString(),
            questionText = "What is your name?",
            questionType = QuestionType.TextInput,
            correctAnswer = "Sir Lancelot the Brave"
        )
        TextInputQuestionContent(modifier = Modifier.fillMaxSize(), question = question)
    }
}