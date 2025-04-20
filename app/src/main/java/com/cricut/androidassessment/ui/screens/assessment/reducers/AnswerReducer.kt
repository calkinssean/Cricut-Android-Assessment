package com.cricut.androidassessment.ui.screens.assessment.reducers

import com.cricut.androidassessment.data.model.answer.Answer
import com.cricut.androidassessment.data.model.answer.MultipleChoiceAnswer
import com.cricut.androidassessment.data.model.answer.TextInputAnswer
import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.Question

class AnswerReducer {

    fun reduce(question: Question, answer: Answer?, value: Any): Answer? {
        return when (question.questionType) {
            QuestionType.MultiAnswerMultipleChoice, QuestionType.SingleAnswerMultipleChoice ->
                updatedMultipleChoiceAnswer(
                    question,
                    answer,
                    value
                )

            QuestionType.TrueFalse -> updateTrueFalseAnswer(question, answer, value)
            QuestionType.TextInput -> updateTextInputAnswer(question, answer, value)
        }
    }

    private fun updatedMultipleChoiceAnswer(
        question: Question,
        answer: Answer?,
        value: Any
    ): MultipleChoiceAnswer? {
        val selectedIndex = (value as? Int) ?: return null
        val currentAnswer = answer as? MultipleChoiceAnswer ?: MultipleChoiceAnswer(
            questionId = question.id,
            questionText = question.questionText,
            questionType = question.questionType,
            selectedIndices = setOf(),
        )
        val updatedIndices = currentAnswer.selectedIndices.toMutableSet()
        if (question.questionType == QuestionType.MultiAnswerMultipleChoice) {
            when {
                updatedIndices.contains(selectedIndex) -> updatedIndices.remove(selectedIndex)
                else -> updatedIndices.add(selectedIndex)
            }
        } else {
            updatedIndices.clear()
            updatedIndices.add(selectedIndex)
        }
        return currentAnswer.copy(selectedIndices = updatedIndices)
    }

    private fun updateTrueFalseAnswer(
        question: Question,
        answer: Answer?,
        value: Any
    ): TrueFalseAnswer? {
        val booleanValue = value as? Boolean ?: return null
        return (answer as? TrueFalseAnswer)?.copy(answer = booleanValue) ?: TrueFalseAnswer(
            questionId = question.id,
            questionText = question.questionText,
            questionType = question.questionType,
            answer = booleanValue,
        )
    }

    private fun updateTextInputAnswer(
        question: Question,
        answer: Answer?,
        value: Any
    ): TextInputAnswer? {
        val textValue = value as? String ?: return null
        return (answer as? TextInputAnswer)?.copy(answer = textValue) ?: TextInputAnswer(
            questionId = question.id,
            questionText = question.questionText,
            questionType = question.questionType,
            answer = textValue,
        )
    }

}