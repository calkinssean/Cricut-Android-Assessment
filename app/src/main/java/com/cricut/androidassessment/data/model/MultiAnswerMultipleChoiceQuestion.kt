package com.cricut.androidassessment.data.model

data class MultiAnswerMultipleChoiceQuestion(
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.MultipleAnswerMultipleChoice,
    val options: List<String>,
    val correctAnswer: Set<Int>
): Question