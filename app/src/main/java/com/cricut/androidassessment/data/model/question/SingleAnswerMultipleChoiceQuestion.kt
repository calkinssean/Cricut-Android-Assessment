package com.cricut.androidassessment.data.model.question

import com.cricut.androidassessment.data.model.common.QuestionType

data class SingleAnswerMultipleChoiceQuestion(
    override val id: String,
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.SingleAnswerMultipleChoice,
    val options: List<String>,
    val correctAnswerIndex: Int
) : Question