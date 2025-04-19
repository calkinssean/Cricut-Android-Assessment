package com.cricut.androidassessment.ui.screens.assessment

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor() : ViewModel() {

    private val mutableModel = MutableStateFlow(AssessmentScreenState(isLoading = true))
    val observableModel: StateFlow<AssessmentScreenState> = mutableModel.asStateFlow()

}
