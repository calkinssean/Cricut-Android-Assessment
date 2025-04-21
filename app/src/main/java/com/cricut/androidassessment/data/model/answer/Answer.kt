package com.cricut.androidassessment.data.model.answer

import com.cricut.androidassessment.data.model.common.QuestionType

interface Answer {
    val questionId: String
    val questionType: QuestionType
}