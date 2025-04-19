package com.cricut.androidassessment.data.repository

import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.TextInputQuestion
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import kotlinx.coroutines.delay
import kotlin.random.Random

class AssessmentRepository {

    suspend fun getQuestions(): List<Question> {
        delay(Random.nextLong(500, 3000))
        return getQuestionsFromLocal()
    }

    private fun getQuestionsFromLocal(): List<Question> {
        return listOf(
            SingleAnswerMultipleChoiceQuestion(
                "What is the capital of France?",
                QuestionType.SingleAnswerMultipleChoice,
                listOf("Paris", "London", "Berlin", "Madrid"),
                0
            ),
            SingleAnswerMultipleChoiceQuestion(
                "Which planet is known as the Red Planet?",
                QuestionType.SingleAnswerMultipleChoice,
                listOf("Jupiter", "Venus", "Mars", "Saturn"),
                2
            ),
            MultiAnswerMultipleChoiceQuestion(
                "Which countries are part of the European Union?",
                QuestionType.MultiAnswerMultipleChoice,
                listOf("Germany", "United Kingdom", "France", "Italy"),
                setOf(0, 2)
            ),
            MultiAnswerMultipleChoiceQuestion(
                "What are the three primary colors?",
                QuestionType.MultiAnswerMultipleChoice,
                listOf("Red", "Green", "Blue", "Yellow"),
                setOf(0, 1, 2)
            ),
            TrueFalseQuestion(
                "Is the Earth a landlocked country?",
                QuestionType.TrueFalse,
                false
            ),
            TrueFalseQuestion(
                "The capital of Australia is Canberra.",
                QuestionType.TrueFalse,
                true
            ),
            TextInputQuestion(
                "What is the largest mammal in the world?",
                QuestionType.TextInput,
                "African Elephant"
            ),
            TextInputQuestion(
                "What is the currency of Japan?",
                QuestionType.TextInput,
                "Japanese Yen"
            )
        )
    }

}