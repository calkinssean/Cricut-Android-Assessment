package com.cricut.androidassessment.ui.common.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@Composable
fun AssessmentButton(
    modifier: Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: String,
    isBusy: Boolean = false
) {
    Button(
        modifier = modifier.height(52.dp),
        enabled = enabled,
        onClick = {
            if (!isBusy) {
                onClick()
            }
        },
    ) {
        if (isBusy) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AssessmentButtonPreview() {
    AndroidAssessmentTheme {
        AssessmentButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Button"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AssessmentButtonPreview_Disabled() {
    AndroidAssessmentTheme {
        AssessmentButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            enabled = false,
            text = "Button"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AssessmentButtonPreview_Busy() {
    AndroidAssessmentTheme {
        AssessmentButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            isBusy = true,
            text = "Button"
        )
    }
}