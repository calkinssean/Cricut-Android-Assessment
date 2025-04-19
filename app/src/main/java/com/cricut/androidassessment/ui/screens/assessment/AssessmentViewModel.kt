package com.cricut.androidassessment.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor() : ViewModel() {
    // TODO implement state variables and functions

    // Remove/Modify this
    private val _uiState = MutableStateFlow<String>("Hello World!")
    val uiState: StateFlow<String> = _uiState.asStateFlow()
}
