package com.cricut.androidassessment.ui.screens.common

import com.cricut.androidassessment.data.model.answer.Answer
import com.cricut.androidassessment.data.model.question.Question

data class AssessmentState(
    val isLoading: Boolean,
    val fetchingResults: Boolean = false,
    val questions: List<Question> = listOf(),
    val answers: Map<String, Answer> = mapOf(),
    val currentQuestionIndex: Int = 0
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)

    val isLastQuestion: Boolean = currentQuestionIndex == questions.size - 1

    val isCurrentQuestionAnswered: Boolean
        get() {
            val questionId = currentQuestion?.id
            return if (questionId != null) answers.containsKey(questionId)
            else false
        }

    val progress: Float
        get() = currentQuestionIndex.toFloat() / questions.size

    val currentAnswer: Answer?
        get() = answers[currentQuestion?.id]

}