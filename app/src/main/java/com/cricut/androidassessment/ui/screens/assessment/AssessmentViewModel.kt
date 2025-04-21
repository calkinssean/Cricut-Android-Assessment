package com.cricut.androidassessment.ui.screens.assessment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.repository.AssessmentRepository
import com.cricut.androidassessment.ui.screens.assessment.reducers.AssessmentStateReducer
import com.cricut.androidassessment.ui.screens.common.AssessmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor(
    private val assessmentRepository: AssessmentRepository,
    private val reducer: AssessmentStateReducer
) : ViewModel() {

    private val mutableModel = MutableStateFlow(reducer.createInitialState())
    val observableModel: StateFlow<AssessmentState> = mutableModel
    private val latestModel: AssessmentState
        get() = mutableModel.value

    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            val questions = assessmentRepository.getQuestions()
            mutableModel.update {
                reducer.updateStateWithQuestions(
                    latestModel,
                    questions
                )
            }
        }
    }

    fun getAssessmentResults() {
        mutableModel.update { reducer.updateStateWithFetchingResults(latestModel) }
        viewModelScope.launch {
            val results = assessmentRepository.getAssessmentResults(
                latestModel.questions,
                latestModel.answers
            )
            mutableModel.update { reducer.updateStateWithResults(latestModel, results) }
        }
    }

    fun onNextClicked() {
        if (latestModel.isLastQuestion) {

        } else {
            mutableModel.update { reducer.updateStateWithNextQuestion(latestModel) }
        }
    }

    fun onPreviousQuestionClicked() {
        mutableModel.update { reducer.updateStateWithPreviousQuestion(latestModel) }
    }

    fun onAnswerValueChanged(question: Question, value: Any) {
        mutableModel.update { reducer.updateAnswersWithValue(latestModel, question, value) }
    }

    fun resetState() {
        mutableModel.update { reducer.createInitialState() }
    }

}
