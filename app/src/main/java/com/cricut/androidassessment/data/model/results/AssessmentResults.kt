package com.cricut.androidassessment.data.model.results

data class AssessmentResults(
    val score: Float,
    val results: List<AssessmentResult>
)

data class AssessmentResult(
    val question: String,
    val correctAnswer: String,
    val answer: String,
    val isCorrect: Boolean
)