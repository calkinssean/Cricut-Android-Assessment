package com.cricut.androidassessment.data.model.answer

import com.cricut.androidassessment.data.model.common.QuestionType

data class TrueFalseAnswer(
    override val questionId: String,
    override val questionText: String,
    override val questionType: QuestionType,
    val answer: Boolean
): Answer