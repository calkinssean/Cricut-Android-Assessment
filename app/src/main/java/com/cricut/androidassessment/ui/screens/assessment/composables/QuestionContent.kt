package com.cricut.androidassessment.ui.screens.assessment.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cricut.androidassessment.data.model.answer.Answer
import com.cricut.androidassessment.data.model.answer.MultipleChoiceAnswer
import com.cricut.androidassessment.data.model.answer.TextInputAnswer
import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.TextInputQuestion
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion

@Composable
fun QuestionContent(
    modifier: Modifier,
    question: Question,
    answer: Answer?,
    onValueChanged: (Question, Any) -> Unit,
) {
    when (question) {
        is MultiAnswerMultipleChoiceQuestion -> MultiAnswerMultipleChoiceQuestionContent(
            modifier = modifier,
            question = question,
            answer = answer as? MultipleChoiceAnswer,
            onValueChanged = onValueChanged
        )

        is SingleAnswerMultipleChoiceQuestion -> SingleAnswerMultipleChoiceQuestionContent(
            modifier = modifier,
            question = question,
            answer = answer as? MultipleChoiceAnswer,
            onValueChanged = onValueChanged
        )

        is TextInputQuestion -> TextInputQuestionContent(
            modifier = modifier,
            question = question,
            answer = answer as? TextInputAnswer,
            onValueChanged = onValueChanged
        )
        is TrueFalseQuestion -> TrueFalseQuestionContent(
            modifier = modifier,
            question = question,
            answer = answer as? TrueFalseAnswer,
            onValueChanged = onValueChanged
        )
    }
}