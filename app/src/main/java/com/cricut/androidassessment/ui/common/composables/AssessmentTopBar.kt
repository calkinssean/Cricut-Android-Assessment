package com.cricut.androidassessment.ui.common.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentTopBar(modifier: Modifier = Modifier, title: String) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(title)
        },
        actions = {

        }

    )
}


@Preview
@Composable
fun AssessmentTopBarPreview() {
    AndroidAssessmentTheme {
        AssessmentTopBar(modifier = Modifier.fillMaxWidth(), title = "Assessment")
    }
}