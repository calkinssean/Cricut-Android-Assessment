package com.cricut.androidassessment.ui.screens.assessment.reducers

import com.cricut.androidassessment.data.model.answer.MultipleChoiceAnswer
import com.cricut.androidassessment.data.model.answer.TextInputAnswer
import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.SingleAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.TextInputQuestion
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class AnswerReducerTest {

    private lateinit var objectUnderTest: AnswerReducer

    @Before
    fun setup() {
        objectUnderTest = AnswerReducer()
    }

    @Test
    fun `reduce returns correct answer for TrueFalseQuestion when existing answer is null`() {
        val question = trueFalseQuestion
        val value = Random.nextBoolean()
        val answer = objectUnderTest.reduce(question, null, value)

        assertTrue(answer is TrueFalseAnswer)
        assertEquals(value, (answer as TrueFalseAnswer).answer)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
    }

    @Test
    fun `reduce returns correct answer for TrueFalseQuestion when existing answer is not null`() {
        val question = trueFalseQuestion
        val existingAnswer = TrueFalseAnswer(
            questionId = question.id,
            questionType = question.questionType,
            answer = Random.nextBoolean()
        )
        val value = Random.nextBoolean()
        val answer = objectUnderTest.reduce(question, existingAnswer, value)

        assertTrue(answer is TrueFalseAnswer)
        assertEquals(value, (answer as TrueFalseAnswer).answer)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
    }

    @Test
    fun `reduce returns correct null for TrueFalseQuestion when value is not a boolean`() {
        val question = trueFalseQuestion
        val value = UUID.randomUUID().toString()
        val answer = objectUnderTest.reduce(question, null, value)

        assertNull(answer)
    }

    @Test
    fun `reduce returns correct answer for MultiAnswerMultipleChoiceQuestion when existing answer is null`() {
        val question = multiAnswerMultipleChoiceQuestion
        val value = Random.nextInt(0, question.options.size)
        val answer = objectUnderTest.reduce(question, null, value)

        assertTrue(answer is MultipleChoiceAnswer)
        assertEquals(setOf(value), (answer as MultipleChoiceAnswer).selectedIndices)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
    }

    @Test
    fun `reduce returns correct answer for MultiAnswerMultipleChoiceQuestion when existing answer is not null`() {
        val question = multiAnswerMultipleChoiceQuestion
        val existingAnswer = MultipleChoiceAnswer(
            questionId = question.id,
            questionType = question.questionType,
            selectedIndices = setOf(0)
        )
        val value = 1
        val answer = objectUnderTest.reduce(question, existingAnswer, value)

        assertTrue(answer is MultipleChoiceAnswer)
        assertEquals(setOf(0, 1), (answer as MultipleChoiceAnswer).selectedIndices)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
    }

    @Test
    fun `reduce deselects previously selected index for MultiAnswerMultipleChoiceQuestion`() {
        val question = multiAnswerMultipleChoiceQuestion
        val existingAnswer = MultipleChoiceAnswer(
            questionId = question.id,
            questionType = question.questionType,
            selectedIndices = setOf(0, 1)
        )
        val value = 0
        val answer = objectUnderTest.reduce(question, existingAnswer, value)

        assertTrue(answer is MultipleChoiceAnswer)
        assertFalse((answer as MultipleChoiceAnswer).selectedIndices.contains(0))
    }

    @Test
    fun `reduce returns correct null for MultiAnswerMultipleChoiceQuestion when value is not an integer`() {
        val question = multiAnswerMultipleChoiceQuestion
        val value = UUID.randomUUID().toString()
        val answer = objectUnderTest.reduce(question, null, value)

        assertNull(answer)
    }

    @Test
    fun `reduce returns correct answer for SingleAnswerMultipleChoiceQuestion`() {
        val question = singleAnswerMultipleChoiceQuestion
        val value = Random.nextInt(0, question.options.size)
        val answer = objectUnderTest.reduce(question, null, value)

        assertTrue(answer is MultipleChoiceAnswer)
        assertEquals(setOf(value), (answer as MultipleChoiceAnswer).selectedIndices)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
    }

    @Test
    fun `reduce returns correct answer for SingleAnswerMultipleChoiceQuestion when existing answer is not null`() {
        val question = singleAnswerMultipleChoiceQuestion
        val existingAnswer = MultipleChoiceAnswer(
            questionId = question.id,
            questionType = question.questionType,
            selectedIndices = setOf(0)
        )
        val value = 1
        val answer = objectUnderTest.reduce(question, existingAnswer, value)

        assertTrue(answer is MultipleChoiceAnswer)
        assertEquals(setOf(1), (answer as MultipleChoiceAnswer).selectedIndices)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
    }


    @Test
    fun `reduce returns correct answer for TextInputQuestion when existing answer is null`() {
        val question = textInputQuestion
        val value = UUID.randomUUID().toString()
        val answer = objectUnderTest.reduce(question, null, value)

        assertTrue(answer is TextInputAnswer)
        assertEquals(value, (answer as TextInputAnswer).answer)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
    }

    @Test
    fun `reduce returns correct answer for TextInputQuestion when existing answer is not null`() {
        val question = textInputQuestion
        val oldAnswer = UUID.randomUUID().toString()
        val existingAnswer = TextInputAnswer(
            questionId = question.id,
            questionType = question.questionType,
            answer = oldAnswer
        )
        val value = UUID.randomUUID().toString()
        val answer = objectUnderTest.reduce(question, existingAnswer, value)

        assertTrue(answer is TextInputAnswer)
        assertEquals(value, (answer as TextInputAnswer).answer)
        assertEquals(question.id, answer.questionId)
        assertEquals(question.questionType, answer.questionType)
        assertNotEquals(oldAnswer, answer.answer)
    }

    @Test
    fun `reduce returns correct null for TextInputQuestion when value is not a string`() {
        val question = textInputQuestion
        val value = Random.nextInt()
        val answer = objectUnderTest.reduce(question, null, value)
        assertNull(answer)
    }

    private val trueFalseQuestion = TrueFalseQuestion(
        id = "1",
        questionText = "Is this a question?",
        questionType = QuestionType.TrueFalse,
        correctAnswer = true
    )

    private val multiAnswerMultipleChoiceQuestion = MultiAnswerMultipleChoiceQuestion(
        id = "2",
        questionText = "Is this a question?",
        questionType = QuestionType.MultiAnswerMultipleChoice,
        correctAnswers = setOf(1, 2),
        options = listOf("Option 1", "Option 2", "Option 3")
    )

    private val singleAnswerMultipleChoiceQuestion = SingleAnswerMultipleChoiceQuestion(
        id = "2",
        questionText = "Is this a question?",
        questionType = QuestionType.SingleAnswerMultipleChoice,
        correctAnswerIndex = 1,
        options = listOf("Option 1", "Option 2", "Option 3")
    )

    private val textInputQuestion = TextInputQuestion(
        id = "3",
        questionText = "What is your name?",
        questionType = QuestionType.TextInput,
        correctAnswer = "John Doe"
    )

}