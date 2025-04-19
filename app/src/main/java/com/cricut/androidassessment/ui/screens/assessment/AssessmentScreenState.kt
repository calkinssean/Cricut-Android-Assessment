package com.cricut.androidassessment.ui.screens.assessment

import com.cricut.androidassessment.data.model.answer.Answer
import com.cricut.androidassessment.data.model.question.Question

data class AssessmentScreenState(
    val isLoading: Boolean,
    val questions: List<Question> = listOf(),
    val answers: List<Answer> = listOf(),
    val currentQuestionIndex: Int = 0
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)

    val isLastQuestion: Boolean = currentQuestionIndex == questions.size - 1

    val isCurrentQuestionAnswered: Boolean
        get() = answers.any { it.questionId == currentQuestion?.questionId }

    val progress: Float
        get() = currentQuestionIndex.toFloat() / questions.size

}