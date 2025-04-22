package com.cricut.androidassessment.ui.screens.assessment.reducers

import android.util.Log
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.results.AssessmentResults
import com.cricut.androidassessment.ui.screens.common.AssessmentState
import javax.inject.Inject

class AssessmentStateReducer @Inject constructor(private val answerReducer: AnswerReducer) {

    fun createInitialState(): AssessmentState = AssessmentState(isLoading = true)
    fun updateStateWithQuestions(
        currentState: AssessmentState,
        questions: List<Question>
    ): AssessmentState = currentState.copy(isLoading = false, questions = questions)

    fun updateStateWithIsBusy(currentState: AssessmentState): AssessmentState {
        return currentState.copy(isBusy = true)
    }

    fun updateStateWithNextQuestion(
        currentState: AssessmentState
    ): AssessmentState =
        currentState.copy(currentQuestionIndex = currentState.currentQuestionIndex + 1, isBusy = false)

    fun updateStateWithPreviousQuestion(
        currentState: AssessmentState
    ): AssessmentState =
        currentState.copy(currentQuestionIndex = currentState.currentQuestionIndex - 1)

    fun updateAnswersWithValue(
        currentState: AssessmentState,
        currentQuestion: Question,
        value: Any
    ): AssessmentState {
        val updatedAnswer = answerReducer.reduce(
            currentQuestion,
            currentState.currentAnswer,
            value
        )
        if (updatedAnswer == null) {
            Log.e("AssessmentStateReducer", "Invalid answer value for question ${currentQuestion.id}, $value")
            return currentState
        }
        val updatedAnswers = currentState.answers.toMutableMap().apply {
            put(currentQuestion.id, updatedAnswer)
        }
        return currentState.copy(answers = updatedAnswers)
    }

    fun updateStateWithResults(
        currentState: AssessmentState,
        results: AssessmentResults
    ): AssessmentState {
        return currentState.copy(fetchingResults = false, results = results)
    }

    fun updateStateWithFetchingResults(currentState: AssessmentState): AssessmentState {
        return currentState.copy(fetchingResults = true)
    }

}