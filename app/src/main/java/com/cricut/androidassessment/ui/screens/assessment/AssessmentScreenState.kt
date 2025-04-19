package com.cricut.androidassessment.ui.screens.assessment

import com.cricut.androidassessment.data.model.question.Question

data class AssessmentScreenState (
    val isLoading: Boolean,
    val questions: List<Question> = listOf(),
    val currentQuestionIndex: Int = 0
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)

}