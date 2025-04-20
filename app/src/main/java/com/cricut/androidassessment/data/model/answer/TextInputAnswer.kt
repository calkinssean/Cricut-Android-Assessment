package com.cricut.androidassessment.data.model.answer

import com.cricut.androidassessment.data.model.common.QuestionType

data class TextInputAnswer(
    override val questionId: String,
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.TextInput,
    val answer: String
): Answer