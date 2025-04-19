package com.cricut.androidassessment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cricut.androidassessment.ui.screens.assessment.AssessmentScreenRoute
import com.cricut.androidassessment.ui.screens.assessment.assessmentScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = AssessmentScreenRoute
    ) {
        assessmentScreen()
    }
}