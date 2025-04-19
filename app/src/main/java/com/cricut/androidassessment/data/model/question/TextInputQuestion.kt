package com.cricut.androidassessment.data.model.question

import com.cricut.androidassessment.data.model.common.QuestionType

data class TextInputQuestion(
    override val questionId: String,
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.TextInput,
    val correctAnswer: String
) : Question