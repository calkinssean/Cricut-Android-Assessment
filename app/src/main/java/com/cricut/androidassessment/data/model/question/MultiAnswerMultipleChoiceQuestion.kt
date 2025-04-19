package com.cricut.androidassessment.data.model.question

import com.cricut.androidassessment.data.model.common.QuestionType

data class MultiAnswerMultipleChoiceQuestion(
    override val questionId: String,
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.MultipleAnswerMultipleChoice,
    val options: List<String>,
    val correctAnswer: Set<Int>
): Question