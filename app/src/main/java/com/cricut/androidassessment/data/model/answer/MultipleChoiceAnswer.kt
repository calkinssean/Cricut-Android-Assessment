package com.cricut.androidassessment.data.model.answer

import com.cricut.androidassessment.data.model.common.QuestionType

data class MultipleChoiceAnswer (
    override val questionId: String,
    override val questionType: QuestionType,
    val selectedIndices: Set<Int>
): Answer