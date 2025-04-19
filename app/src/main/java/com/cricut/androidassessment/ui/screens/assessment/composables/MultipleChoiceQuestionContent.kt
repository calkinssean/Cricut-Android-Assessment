package com.cricut.androidassessment.ui.screens.assessment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.ui.common.composables.AssessmentButton

@Composable
fun MultipleChoiceQuestionContent(
    modifier: Modifier = Modifier,
    question: Question
) {
    Column(modifier = Modifier) {
        Text(question.questionText)
    }
}

@Preview
@Composable
fun MultipleChoiceQuestionContentPreview() {
}