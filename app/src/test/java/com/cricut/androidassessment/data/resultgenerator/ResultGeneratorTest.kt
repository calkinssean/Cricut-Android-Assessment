package com.cricut.androidassessment.data.resultgenerator

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
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultGeneratorTest {

    private val objectUnderTest = ResultGenerator()

    @Test
    fun `generateResult returns correct result for TrueFalseQuestion when answer is correct`() {
        val question = TrueFalseQuestion(
            id = "1",
            questionText = "Is this a question?",
            questionType = QuestionType.TrueFalse,
            correctAnswer = true
        )
        val answer = TrueFalseAnswer(
            questionId = question.id,
            questionType = question.questionType,
            answer = true
        )
        val result = objectUnderTest.generateResult(question, answer)!!

        assertNotNull(result)
        assertTrue(result.isCorrect)
        assertEquals(question.questionText, result.question)
        assertEquals(question.correctAnswer.toString(), result.correctAnswer)
        assertEquals(answer.answer.toString(), result.answer)
    }

    @Test
    fun `generateResult returns correct result for TrueFalseQuestion when answer is incorrect`() {
        val question = TrueFalseQuestion(
            id = "1",
            questionText = "Is this a question?",
            questionType = QuestionType.TrueFalse,
            correctAnswer = true
        )
        val answer = TrueFalseAnswer(
            questionId = question.id,
            questionType = question.questionType,
            answer = false
        )
        val result = objectUnderTest.generateResult(question, answer)!!

        assertNotNull(result)
        assertFalse(result.isCorrect)
        assertEquals(question.questionText, result.question)
        assertEquals(question.correctAnswer.toString(), result.correctAnswer)
    }

    @Test
    fun `generateResult returns null when answer is the wrong type`() {
        val question = TrueFalseQuestion(
            id = "1",
            questionText = "Is this a question?",
            questionType = QuestionType.TrueFalse,
            correctAnswer = true
        )
        val answer = TextInputAnswer(
            questionId = question.id,
            questionType = question.questionType,
            answer = "true"
        )
        val result = objectUnderTest.generateResult(question, answer)

        assertNull(result)
    }

    @Test
    fun `generateResult returns correct result for TextInputQuestion when answer is correct`() {
        val question = TextInputQuestion(
            id = "1",
            questionText = "What is the capital of France?",
            questionType = QuestionType.TextInput,
            correctAnswer = "Paris"
        )
        val answer = TextInputAnswer(
            questionId = question.id,
            questionType = question.questionType,
            answer = "Paris"
        )
        val result = objectUnderTest.generateResult(question, answer)!!

        assertNotNull(result)
        assertTrue(result.isCorrect)
        assertEquals(question.questionText, result.question)
        assertEquals(question.correctAnswer, result.correctAnswer)
    }

    @Test
    fun `generateResult returns correct result for TextInputQuestion when answer is incorrect`() {
        val question = TextInputQuestion(
            id = "1",
            questionText = "What is the capital of France?",
            questionType = QuestionType.TextInput,
            correctAnswer = "Paris"
        )
        val answer = TextInputAnswer(
            questionId = question.id,
            questionType = question.questionType,
            answer = "London"
        )
        val result = objectUnderTest.generateResult(question, answer)!!

        assertNotNull(result)
        assertFalse(result.isCorrect)
        assertEquals(question.questionText, result.question)
        assertEquals(question.correctAnswer, result.correctAnswer)
    }

    @Test
    fun `generateResult returns correct result for SingleAnswerMultipleChoiceQuestion when answer is correct`() {
        val question = SingleAnswerMultipleChoiceQuestion(
            id = "1",
            questionText = "What is the capital of France?",
            questionType = QuestionType.SingleAnswerMultipleChoice,
            correctAnswerIndex = 0,
            options = listOf("Paris", "London", "Berlin")
        )
        val answer = MultipleChoiceAnswer(
            questionId = question.id,
            questionType = question.questionType,
            selectedIndices = setOf(0)
        )
         val result = objectUnderTest.generateResult(question, answer)!!

         assertNotNull(result)
         assertTrue(result.isCorrect)
         assertEquals(question.questionText, result.question)
         assertEquals(question.options[question.correctAnswerIndex], result.correctAnswer)
    }

    @Test
    fun `generateResult returns correct result for SingleAnswerMultipleChoiceQuestion when answer is incorrect`() {
        val question = SingleAnswerMultipleChoiceQuestion(
            id = "1",
            questionText = "What is the capital of France?",
            questionType = QuestionType.SingleAnswerMultipleChoice,
            correctAnswerIndex = 0,
            options = listOf("Paris", "London", "Berlin")
        )
        val answer = MultipleChoiceAnswer(
            questionId = question.id,
            questionType = question.questionType,
            selectedIndices = setOf(1)
        )
        val result = objectUnderTest.generateResult(question, answer)!!

        assertNotNull(result)
        assertFalse(result.isCorrect)
        assertEquals(question.questionText, result.question)
        assertEquals(question.options[question.correctAnswerIndex], result.correctAnswer)
    }

    @Test
    fun `generateResult returns correct result for MultiAnswerMultipleChoiceQuestion when answer is correct`() {
        val question = MultiAnswerMultipleChoiceQuestion(
            id = "1",
            questionText = "Should I keep writing unit tests?",
            questionType = QuestionType.MultiAnswerMultipleChoice,
            correctAnswers = setOf(1, 3),
            options = listOf("Yes", "No", "Maybe", "Write Some UI Tests Instead")
        )
        val answer = MultipleChoiceAnswer(
            questionId = question.id,
            questionType = question.questionType,
            selectedIndices = setOf(1, 3)
        )
        val result = objectUnderTest.generateResult(question, answer)!!

        assertNotNull(result)
        assertTrue(result.isCorrect)
        assertEquals(question.questionText, result.question)
        val expectedAnswersText = question.correctAnswers.map { question.options[it] }.joinToString(", ") { it }
        assertEquals(expectedAnswersText, result.correctAnswer)
    }

    @Test
    fun `generateResult returns correct result for MultiAnswerMultipleChoiceQuestion when answer is incorrect`() {
        val question = MultiAnswerMultipleChoiceQuestion(
            id = "1",
            questionText = "Should I keep writing unit tests?",
            questionType = QuestionType.MultiAnswerMultipleChoice,
            correctAnswers = setOf(1, 3),
            options = listOf("Yes", "No", "Maybe", "Write Some UI Tests Instead")
        )
        val answer = MultipleChoiceAnswer(
            questionId = question.id,
            questionType = question.questionType,
            selectedIndices = setOf(0, 2)
        )
        val result = objectUnderTest.generateResult(question, answer)!!

        assertNotNull(result)
        assertFalse(result.isCorrect)
        assertEquals(question.questionText, result.question)
    }

}