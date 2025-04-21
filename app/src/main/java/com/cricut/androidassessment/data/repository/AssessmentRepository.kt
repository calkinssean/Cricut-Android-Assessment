package com.cricut.androidassessment.data.repository

import com.cricut.androidassessment.data.model.answer.Answer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.TextInputQuestion
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.data.model.results.AssessmentResults
import com.cricut.androidassessment.data.resultgenerator.ResultGenerator
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject
import kotlin.random.Random

class AssessmentRepository @Inject constructor(
    private val resultGenerator: ResultGenerator
) {

    suspend fun getQuestions(): List<Question> {
        delay(Random.nextLong(500, 3000))
        return getQuestionsFromLocal()
    }

    suspend fun getAssessmentResults(
        questions: List<Question>,
        answers: Map<String, Answer>
    ): AssessmentResults {
        delay(Random.nextLong(500, 3000))
        val questionAnswerMap = questions.map { question ->
            val answer = answers[question.id]
            question to answer
        }
        val results = questionAnswerMap.mapNotNull { (question, answer) ->
            resultGenerator.generateResult(question, answer)
        }
        val correctCount = results.count { it.isCorrect }
        val totalQuestions = questions.size
        val score = if (totalQuestions > 0) {
            correctCount.toFloat() / totalQuestions.toFloat()
        } else {
            0f
        }
        return AssessmentResults(
            score = score,
            results = results
        )
    }

    private fun getQuestionsFromLocal(): List<Question> {
        return listOf(
            SingleAnswerMultipleChoiceQuestion(
                UUID.randomUUID().toString(),
                "What is the capital of France?",
                QuestionType.SingleAnswerMultipleChoice,
                listOf("Paris", "London", "Berlin", "Madrid"),
                0
            ),
            SingleAnswerMultipleChoiceQuestion(
                UUID.randomUUID().toString(),
                "Which planet is known as the Red Planet?",
                QuestionType.SingleAnswerMultipleChoice,
                listOf("Jupiter", "Venus", "Mars", "Saturn"),
                2
            ),
            MultiAnswerMultipleChoiceQuestion(
                UUID.randomUUID().toString(),
                "Which countries are part of the European Union?",
                QuestionType.MultiAnswerMultipleChoice,
                listOf("Germany", "United Kingdom", "France", "Italy"),
                setOf(0, 2)
            ),
            MultiAnswerMultipleChoiceQuestion(
                UUID.randomUUID().toString(),
                "What are the three primary colors?",
                QuestionType.MultiAnswerMultipleChoice,
                options = listOf("Red", "Green", "Blue", "Yellow", "Orange"),
                setOf(0, 2, 3)
            ),
            TrueFalseQuestion(
                UUID.randomUUID().toString(),
                "The sky is blue",
                QuestionType.TrueFalse,
                true
            ),
            TrueFalseQuestion(
                UUID.randomUUID().toString(),
                "The capital of Australia is Canberra.",
                QuestionType.TrueFalse,
                true
            ),
            TextInputQuestion(
                UUID.randomUUID().toString(),
                "What is the largest mammal in the world?",
                QuestionType.TextInput,
                "African Elephant"
            ),
            TextInputQuestion(
                UUID.randomUUID().toString(),
                "What is the currency of Japan?",
                QuestionType.TextInput,
                "Japanese Yen"
            )
        )
    }

}