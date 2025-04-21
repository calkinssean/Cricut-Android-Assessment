package com.cricut.androidassessment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cricut.androidassessment.ui.screens.assessment.AssessmentScreen
import com.cricut.androidassessment.ui.screens.assessment.AssessmentScreenRoute
import com.cricut.androidassessment.ui.screens.assessment.AssessmentViewModel
import com.cricut.androidassessment.ui.screens.results.ResultsScreen
import com.cricut.androidassessment.ui.screens.results.ResultsScreenRoute

private const val AssessmentGraph = "AssessmentGraph"

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AssessmentGraph
    ) {
        assessmentNavigationGraph(navController)
    }
}

private fun NavGraphBuilder.assessmentNavigationGraph(navController: NavHostController) {
    navigation(
        startDestination = AssessmentScreenRoute,
        route = AssessmentGraph
    ) {
        composable(AssessmentScreenRoute) { entry ->
            val parentEntry = remember(entry) {
                navController.getBackStackEntry(AssessmentGraph)
            }
            val viewModel: AssessmentViewModel = hiltViewModel(parentEntry)
            AssessmentScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                onAssessmentComplete = {
                    navController.navigate(ResultsScreenRoute)
                }
            )
        }

        composable(ResultsScreenRoute) { entry ->
            val parentEntry = remember(entry) {
                navController.getBackStackEntry(AssessmentGraph)
            }
            val viewModel: AssessmentViewModel = hiltViewModel(parentEntry)
            ResultsScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel
            )
        }
    }
}