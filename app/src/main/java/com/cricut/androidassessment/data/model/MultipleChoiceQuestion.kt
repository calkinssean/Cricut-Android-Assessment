package com.cricut.androidassessment.data.model

data class MultipleChoiceQuestion(
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.MultipleChoice,
    val choices: List<String>,
    val correctAnswer: String
) : Question