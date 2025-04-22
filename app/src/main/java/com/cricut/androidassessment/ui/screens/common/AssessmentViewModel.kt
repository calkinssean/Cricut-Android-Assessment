package com.cricut.androidassessment.ui.screens.common

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.repository.AssessmentRepository
import com.cricut.androidassessment.ui.screens.assessment.reducers.AssessmentStateReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AssessmentViewModel
@Inject constructor(
    private val assessmentRepository: AssessmentRepository,
    private val reducer: AssessmentStateReducer
) : ViewModel() {

    @VisibleForTesting
    val mutableModel = MutableStateFlow(reducer.createInitialState())
    val observableModel: StateFlow<AssessmentState> = mutableModel
    @VisibleForTesting
    val latestModel: AssessmentState
        get() = mutableModel.value

    fun fetchQuestions() {
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
        viewModelScope.launch {
            mutableModel.update { reducer.updateStateWithIsBusy(latestModel) }
            delay(Random.nextLong(0, 1000))
            if (!latestModel.isLastQuestion) {
                mutableModel.update { reducer.updateStateWithNextQuestion(latestModel) }
            }
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
