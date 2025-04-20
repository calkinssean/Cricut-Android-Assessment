package com.cricut.androidassessment.data.model.question

import com.cricut.androidassessment.data.model.common.QuestionType

interface Question {
    val id: String
    val questionText: String
    val questionType: QuestionType
}