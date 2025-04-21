package com.cricut.androidassessment.ui.screens.results.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.R
import com.cricut.androidassessment.data.model.results.AssessmentResult
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@Composable
fun ResultListItem(modifier: Modifier = Modifier, result: AssessmentResult) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (result.isCorrect) {
            Icon(
                painter = painterResource(R.drawable.baseline_check_circle_24),
                contentDescription = "Correct Answer",
                modifier = Modifier.padding(8.dp),
                tint = Color.Green
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.baseline_cancel_24),
                contentDescription = "Incorrect Answer",
                modifier = Modifier.padding(8.dp),
                tint = Color.Red
            )
        }
        Column {
            Text(text = result.question, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Correct answer: ${result.correctAnswer}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Your answer: ${result.answer}", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ResultItemPreview() {
    val results = listOf(
        AssessmentResult(question = "Question 1", "1", "2", false),
        AssessmentResult(question = "Question 2", "2", "2", true),
        AssessmentResult(question = "Question 3", "3", "2", false)
    )
    AndroidAssessmentTheme {
        Column {
            results.forEach {
                ResultListItem(modifier = Modifier.fillMaxWidth(), it)
            }
        }
    }
}