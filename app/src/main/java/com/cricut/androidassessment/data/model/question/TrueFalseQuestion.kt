package com.cricut.androidassessment.data.model.question

import com.cricut.androidassessment.data.model.common.QuestionType

data class TrueFalseQuestion(
    override val id: String,
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.TrueFalse,
    val correctAnswer: Boolean
) : Question