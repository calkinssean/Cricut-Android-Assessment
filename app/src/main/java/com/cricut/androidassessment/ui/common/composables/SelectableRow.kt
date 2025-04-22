package com.cricut.androidassessment.ui.common.composables

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@VisibleForTesting
object SelectableRowTestTags {
    val rowTestTag: String = "rowTestTag"
    val checkboxTestTag: String = "checkboxTestTag"
    val textTestTag: String = "textTestTag"
}

@Composable
fun SelectableRow(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .testTag(SelectableRowTestTags.rowTestTag)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            modifier = Modifier.testTag(SelectableRowTestTags.checkboxTestTag),
            checked = checked,
            onCheckedChange = {
                onClick()
            }
        )
        Text(
            modifier = Modifier.testTag(SelectableRowTestTags.textTestTag),
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableRowPreview() {
    AndroidAssessmentTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SelectableRow(text = "Selected", checked = true, onClick = {})
            SelectableRow(text = "Not Selected", checked = false, onClick = {})
        }
    }
}