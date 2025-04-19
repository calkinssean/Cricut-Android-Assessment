package com.cricut.androidassessment.data.model.question

import com.cricut.androidassessment.data.model.common.QuestionType

data class SingleAnswerMultipleChoiceQuestion(
    override val questionId: String,
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.SingleAnswerMultipleChoice,
    val choices: List<String>,
    val correctAnswerIndex: Int
) : Question