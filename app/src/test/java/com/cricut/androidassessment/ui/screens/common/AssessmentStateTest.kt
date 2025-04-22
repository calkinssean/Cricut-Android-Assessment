package com.cricut.androidassessment.ui.screens.common

import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class AssessmentStateTest {

    private lateinit var objectUnderTest: AssessmentState

    @Before
    fun setup() {
        objectUnderTest = AssessmentState(isLoading = true)
    }

    @Test
    fun `currentQuestion returns question at the correct index`() {
        val questionIndex = Random.nextInt(0, questions.size)
        objectUnderTest = objectUnderTest.copy(questions = questions, currentQuestionIndex = questionIndex)

        val question = objectUnderTest.currentQuestion

        assertEquals(questions[questionIndex], question)
    }

    @Test
    fun `isCurrentQuestionAnswered returns true when answer is not null`() {
        val answers = mapOf(questions[0].id to TrueFalseAnswer(questions[0].id, questions[0].questionType, true))

        objectUnderTest = objectUnderTest.copy(questions = questions, answers = answers)

        assertTrue(objectUnderTest.isCurrentQuestionAnswered)
    }

    @Test
    fun `isCurrentQuestionAnswered returns false when answer is null`() {
        objectUnderTest = objectUnderTest.copy(questions = questions)
        assertFalse(objectUnderTest.isCurrentQuestionAnswered)
    }

    @Test
    fun `isLastQuestion returns true when current question index is the last question`() {
        objectUnderTest = objectUnderTest.copy(questions = questions, currentQuestionIndex = questions.size - 1)
        assertTrue(objectUnderTest.isLastQuestion)
    }

    @Test
    fun `isLastQuestion returns false when current question index is not the last question`() {
        objectUnderTest = objectUnderTest.copy(questions = questions, currentQuestionIndex = questions.size - 2)
        assertFalse(objectUnderTest.isLastQuestion)
    }

    private val questions: List<Question>
        get() = listOf(
            TrueFalseQuestion(
                id = "1",
                questionText = "Is this a question?",
                questionType = QuestionType.TrueFalse,
                correctAnswer = true
            ),
            MultiAnswerMultipleChoiceQuestion(
                id = "2",
                questionText = "Is this a question?",
                questionType = QuestionType.MultiAnswerMultipleChoice,
                correctAnswers = setOf(1, 2),
                options = listOf("Option 1", "Option 2", "Option 3")
            )
        )



}