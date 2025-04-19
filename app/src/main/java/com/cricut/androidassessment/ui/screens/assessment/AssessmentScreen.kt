package com.cricut.androidassessment.ui.screens.assessment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cricut.androidassessment.ui.screens.common.LoadingScreen
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

const val AssessmentScreenRoute = "AssessmentScreenRoute"

fun NavGraphBuilder.assessmentScreen() {
    composable(
        route = AssessmentScreenRoute
    ) { backStackEntry ->

        val viewModel: AssessmentViewModel = hiltViewModel()
        val uiState by viewModel.observableModel.collectAsStateWithLifecycle()

        AssessmentScreen(
            modifier = Modifier.fillMaxSize(),
            state = uiState
        )
    }
}

@Composable
private fun AssessmentScreen(
    modifier: Modifier = Modifier,
    state: AssessmentScreenState
) {
    when {
        state.isLoading -> LoadingScreen(modifier = modifier)
        else -> AssessmentScreenContent(modifier = modifier)
    }

    // TODO implement Compose UI


}

@Composable
private fun AssessmentScreenContent(
    modifier: Modifier = Modifier
) {

}

@Preview(showBackground = true)
@Composable
private fun PreviewAssessmentScreen() {
    val state = AssessmentScreenState(
        isLoading = true
    )
    AndroidAssessmentTheme {
        AssessmentScreen(
            modifier = Modifier.fillMaxSize(),
            state = state
        )
    }
}
