package com.cricut.androidassessment.ui.screens.assessment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricut.androidassessment.data.repository.AssessmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor(
    private val assessmentRepository: AssessmentRepository
) : ViewModel() {

    private val mutableModel = MutableStateFlow(AssessmentScreenState(isLoading = true))
    val observableModel: StateFlow<AssessmentScreenState> = mutableModel
    private val latestModel: AssessmentScreenState
        get() = mutableModel.value

    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            val questions = assessmentRepository.getQuestions()
            mutableModel.update { it.copy(isLoading = false, questions = questions) }
        }
    }

    fun onNextClicked() {
        if (latestModel.isLastQuestion) {

        } else {
            mutableModel.update { it.copy(currentQuestionIndex = it.currentQuestionIndex + 1) }
        }
        Log.d("ViewModel", "Next clicked. ${latestModel.currentQuestionIndex}, ${latestModel.questions.size}")
    }


}
