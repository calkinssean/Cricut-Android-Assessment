package com.cricut.androidassessment.data.model

data class TextInputQuestion(
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.TextInput
) : Question