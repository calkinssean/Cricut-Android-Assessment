package com.cricut.androidassessment.ui.screens.assessment.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.TextInputQuestion
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion

@Composable
fun QuestionContent(
    modifier: Modifier,
    question: Question
) {
    when (question) {
        is MultiAnswerMultipleChoiceQuestion -> MultiAnswerMultipleChoiceQuestionContent(
            modifier,
            question
        )

        is SingleAnswerMultipleChoiceQuestion -> SingleAnswerMultipleChoiceQuestionContent(
            modifier,
            question
        )

        is TextInputQuestion -> TextInputQuestionContent(modifier, question)
        is TrueFalseQuestion -> TrueFalseQuestionContent(modifier, question)
    }
}