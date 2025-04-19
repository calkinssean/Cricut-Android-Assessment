package com.cricut.androidassessment.data.model

data class SingleAnswerMultipleChoiceQuestion(
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.SingleAnswerMultipleChoice,
    val choices: List<String>,
    val correctAnswerIndex: Int
) : Question