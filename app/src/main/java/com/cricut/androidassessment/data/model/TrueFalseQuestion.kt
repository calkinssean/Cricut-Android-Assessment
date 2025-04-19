package com.cricut.androidassessment.data.model

data class TrueFalseQuestion(
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.TrueFalse,
    val correctAnswer: Boolean
) : Question