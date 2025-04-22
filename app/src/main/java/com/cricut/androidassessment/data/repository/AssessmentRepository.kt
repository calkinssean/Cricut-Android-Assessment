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
                "Which component is the entry point for an Android app with a UI?",
                QuestionType.SingleAnswerMultipleChoice,
                listOf("Service", "Activity", "BroadcastReceiver", "ContentProvider"),
                1
            ),
            SingleAnswerMultipleChoiceQuestion(
                UUID.randomUUID().toString(),
                "In Jetpack Compose, what is the function used to define a UI element?",
                QuestionType.SingleAnswerMultipleChoice,
                listOf("View", "Component", "Composable", "Widget"),
                2
            ),
            MultiAnswerMultipleChoiceQuestion(
                UUID.randomUUID().toString(),
                "Which of the following are benefits of using Kotlin for Android development?",
                QuestionType.MultiAnswerMultipleChoice,
                listOf("Null safety", "Full Java interoperability", "Verbose syntax", "Built-in coroutines support"),
                setOf(0, 1, 3)
            ),
            MultiAnswerMultipleChoiceQuestion(
                UUID.randomUUID().toString(),
                "Which of the following are common Jetpack libraries?",
                QuestionType.MultiAnswerMultipleChoice,
                listOf("Retrofit", "Room", "Navigation", "Picasso"),
                setOf(1, 2)
            ),
            TrueFalseQuestion(
                UUID.randomUUID().toString(),
                "A Composable function can return a value?",
                QuestionType.TrueFalse,
                false
            ),
            TrueFalseQuestion(
                UUID.randomUUID().toString(),
                "`findViewById()` is the recommended way to access UI elements in modern Android development with View Binding or Data Binding?",
                QuestionType.TrueFalse,
                false
            ),
            TextInputQuestion(
                UUID.randomUUID().toString(),
                "What does the acronym 'MVVM' stand for in Android architecture?",
                QuestionType.TextInput,
                "Model-View-ViewModel"
            ),
            TextInputQuestion(
                UUID.randomUUID().toString(),
                "Name one advantage of using ConstraintLayout.",
                QuestionType.TextInput,
                "reduced nesting"
            )
        )
    }

}