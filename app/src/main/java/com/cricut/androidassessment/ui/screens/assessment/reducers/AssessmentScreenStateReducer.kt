package com.cricut.androidassessment.ui.screens.assessment.reducers

import android.util.Log
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.ui.screens.assessment.AssessmentScreenState
import javax.inject.Inject

class AssessmentScreenStateReducer @Inject constructor(private val answerReducer: AnswerReducer) {

    fun createInitialState(): AssessmentScreenState = AssessmentScreenState(isLoading = true)
    fun updateStateWithQuestions(
        currentState: AssessmentScreenState,
        questions: List<Question>
    ): AssessmentScreenState = currentState.copy(isLoading = false, questions = questions)

    fun updateStateWithNextQuestion(
        currentState: AssessmentScreenState
    ): AssessmentScreenState =
        currentState.copy(currentQuestionIndex = currentState.currentQuestionIndex + 1)

    fun updateStateWithPreviousQuestion(
        currentState: AssessmentScreenState
    ): AssessmentScreenState =
        currentState.copy(currentQuestionIndex = currentState.currentQuestionIndex - 1)

    fun updateAnswersWithValue(
        currentState: AssessmentScreenState,
        currentQuestion: Question,
        value: Any
    ): AssessmentScreenState {
        val updatedAnswer = answerReducer.reduce(
            currentQuestion,
            currentState.currentAnswer,
            value
        )
        if (updatedAnswer == null) {
            Log.e("AssessmentScreenStateReducer", "Invalid answer value for question ${currentQuestion.id}, $value")
            return currentState
        }
        val updatedAnswers = currentState.answers.toMutableMap().apply {
            put(currentQuestion.id, updatedAnswer)
        }
        return currentState.copy(answers = updatedAnswers)
    }

}