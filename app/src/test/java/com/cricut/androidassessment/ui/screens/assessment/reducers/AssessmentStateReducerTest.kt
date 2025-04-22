package com.cricut.androidassessment.ui.screens.assessment.reducers

import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.data.model.results.AssessmentResults
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import kotlin.random.Random

class AssessmentStateReducerTest {

    private val reducerSpy = spy(AnswerReducer())
    private lateinit var objectUnderTest: AssessmentStateReducer

    @Before
    fun setup() {
        objectUnderTest = AssessmentStateReducer(reducerSpy)
    }

    @Test
    fun `createInitialState returns correct initial state`() {

        val initialState = objectUnderTest.createInitialState()

        assertTrue(initialState.isLoading)
        assertFalse(initialState.fetchingResults)
        assertTrue(initialState.questions.isEmpty())
        assertTrue(initialState.answers.isEmpty())
        assertNull(initialState.results)
        assertEquals(0, initialState.currentQuestionIndex)
    }

    @Test
    fun `updateStateWithQuestions returns correct state`() {
        val initialState = objectUnderTest.createInitialState()
        val questions = questions
        val updatedState = objectUnderTest.updateStateWithQuestions(initialState, questions)

        assertEquals(questions, updatedState.questions)
        assertFalse(updatedState.isLoading)
    }

    @Test
    fun `updateStateWithNextQuestion returns correct state`() {
        val initialState = objectUnderTest.createInitialState().copy(currentQuestionIndex = Random.nextInt(0, 50))
        val updatedState = objectUnderTest.updateStateWithNextQuestion(initialState)

        assertEquals(initialState.currentQuestionIndex + 1, updatedState.currentQuestionIndex)
    }

    @Test
    fun `updateStateWithPreviousQuestion returns correct state`() {
        val initialState = objectUnderTest.createInitialState().copy(currentQuestionIndex = Random.nextInt(1, 50))
        val updatedState = objectUnderTest.updateStateWithPreviousQuestion(initialState)

        assertEquals(initialState.currentQuestionIndex - 1, updatedState.currentQuestionIndex)
    }

    @Test
    fun `updateAnswersWithValue returns correct state`() {
        val initialState = objectUnderTest.createInitialState()
        val question = questions[0]
        val value = true
        val updatedState = objectUnderTest.updateAnswersWithValue(initialState, question, value)

        val updatedAnswer = updatedState.answers[question.id] as? TrueFalseAnswer
        assertEquals(value, updatedAnswer?.answer)
        assertEquals(1, updatedState.answers.size)
    }

    @Test
    fun `updateStateWithResults returns correct state`() {
        val initialState = objectUnderTest.createInitialState().copy(fetchingResults = true)
        val results = AssessmentResults(
            score = 0.5f,
            listOf()
        )
        val updatedState = objectUnderTest.updateStateWithResults(initialState, results)
        assertEquals(results, updatedState.results)
        assertFalse(updatedState.fetchingResults)
    }

    @Test
    fun `updateStateWithFetchingResults returns correct state`() {
        val initialState = objectUnderTest.createInitialState().copy(fetchingResults = false)
        val updatedState = objectUnderTest.updateStateWithFetchingResults(initialState)
        assertTrue(updatedState.fetchingResults)
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