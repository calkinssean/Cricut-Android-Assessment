package com.cricut.androidassessment.data.model.question

import com.cricut.androidassessment.data.model.common.QuestionType

data class MultiAnswerMultipleChoiceQuestion(
    override val id: String,
    override val questionText: String,
    override val questionType: QuestionType = QuestionType.MultiAnswerMultipleChoice,
    val options: List<String>,
    val correctAnswers: Set<Int>
): Question