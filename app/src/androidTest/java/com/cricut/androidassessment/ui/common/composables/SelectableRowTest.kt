package com.cricut.androidassessment.ui.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import org.junit.Rule
import org.junit.Test
import java.util.UUID


class SelectableRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Composable
    private fun TestSelectableRow(
        text: String,
        checked: Boolean,
        onClick: () -> Unit
    ) {
        AndroidAssessmentTheme {
            SelectableRow(
                text = text,
                checked = checked,
                onClick = onClick
            )
        }
    }

    @Test
    fun testSelectableRow_whenIsChecked() {
        val text = UUID.randomUUID().toString()
        composeTestRule.setContent {
            TestSelectableRow(text = text, checked = true, onClick = {})
        }

        composeTestRule.onNodeWithTag(SelectableRowTestTags.rowTestTag)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(SelectableRowTestTags.checkboxTestTag, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertIsOn()

        composeTestRule.onNodeWithTag(SelectableRowTestTags.textTestTag, useUnmergedTree = true)
            .assertExists()
            .assertTextEquals(text)

    }

    @Test
    fun testSelectableRow_whenIsNotChecked() {
        val text = UUID.randomUUID().toString()
        composeTestRule.setContent {
            TestSelectableRow(text = text, checked = false, onClick = {})
        }

        composeTestRule.onNodeWithTag(SelectableRowTestTags.rowTestTag)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(SelectableRowTestTags.checkboxTestTag, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertIsOff()

        composeTestRule.onNodeWithTag(SelectableRowTestTags.textTestTag, useUnmergedTree = true)
            .assertExists()
            .assertTextEquals(text)

    }

    @Test
    fun testSelectableRow_whenClicked() {
        var clicks = 0
        composeTestRule.setContent {
            TestSelectableRow(text = UUID.randomUUID().toString(), checked = false, onClick = {
                clicks++
            })
        }

        composeTestRule.onNodeWithTag(SelectableRowTestTags.rowTestTag).performClick()

        composeTestRule.onNodeWithTag(SelectableRowTestTags.checkboxTestTag, useUnmergedTree = true).performClick()

        assert(clicks == 2)
    }


}