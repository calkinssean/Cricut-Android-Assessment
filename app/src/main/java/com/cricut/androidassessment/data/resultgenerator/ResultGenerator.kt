package com.cricut.androidassessment.data.resultgenerator

import com.cricut.androidassessment.data.model.answer.Answer
import com.cricut.androidassessment.data.model.answer.MultipleChoiceAnswer
import com.cricut.androidassessment.data.model.answer.TextInputAnswer
import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.TextInputQuestion
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.data.model.results.AssessmentResult
import javax.inject.Inject

class ResultGenerator @Inject constructor() {

    fun generateResult(question: Question, answer: Answer?): AssessmentResult? {
        return when (question) {
            is TextInputQuestion -> generateTextInputResult(question, answer)
            is TrueFalseQuestion -> generateTrueFalseResult(question, answer)
            is MultiAnswerMultipleChoiceQuestion -> generateMultiAnswerMultipleChoiceResult(
                question,
                answer
            )

            is SingleAnswerMultipleChoiceQuestion -> generateSingleAnswerMultipleChoiceResult(
                question,
                answer
            )

            else -> null
        }
    }

    private fun generateTextInputResult(
        question: TextInputQuestion,
        answer: Answer?
    ): AssessmentResult? {
        val safeAnswer = answer as? TextInputAnswer ?: return null
        val normalizedCorrectAnswer = question.correctAnswer.lowercase().replace(" ", "").replace("-", "")
        val normalizedUserAnswer = safeAnswer.answer.lowercase().replace(" ", "").replace("-", "")
        return AssessmentResult(
            question = question.questionText,
            correctAnswer = question.correctAnswer,
            answer = safeAnswer.answer,
            isCorrect = normalizedCorrectAnswer == normalizedUserAnswer
        )
    }

    private fun generateTrueFalseResult(
        question: TrueFalseQuestion,
        answer: Answer?
    ): AssessmentResult? {
        val safeAnswer = answer as? TrueFalseAnswer ?: return null
        return AssessmentResult(
            question = question.questionText,
            correctAnswer = question.correctAnswer.toString(),
            answer = safeAnswer.answer.toString(),
            isCorrect = question.correctAnswer == safeAnswer.answer
        )
    }

    private fun generateSingleAnswerMultipleChoiceResult(
        question: SingleAnswerMultipleChoiceQuestion,
        answer: Answer?
    ): AssessmentResult? {
        val safeAnswer = answer as? MultipleChoiceAnswer ?: return null
        val correctAnswer =
            question.options[question.correctAnswerIndex]
        val selectedIndex = safeAnswer.selectedIndices.firstOrNull() ?: return null
        val selectedAnswer = question.options[selectedIndex]
        return AssessmentResult(
            question = question.questionText,
            correctAnswer = correctAnswer,
            answer = selectedAnswer,
            isCorrect = correctAnswer == selectedAnswer
        )
    }

    private fun generateMultiAnswerMultipleChoiceResult(
        question: MultiAnswerMultipleChoiceQuestion,
        answer: Answer?
    ): AssessmentResult? {
        val safeAnswer = answer as? MultipleChoiceAnswer ?: return null
        val correctAnswers = question.correctAnswers.map { question.options[it] }
        val selectedAnswers =
            safeAnswer.selectedIndices.map { question.options[it] }
        return AssessmentResult(
            question = question.questionText,
            correctAnswer = correctAnswers.joinToString(", "),
            answer = selectedAnswers.joinToString(", "),
            isCorrect = question.correctAnswers.containsAll(safeAnswer.selectedIndices)
        )
    }

}