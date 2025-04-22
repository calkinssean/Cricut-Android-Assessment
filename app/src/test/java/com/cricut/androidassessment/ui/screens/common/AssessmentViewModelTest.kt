package com.cricut.androidassessment.ui.screens.common

import com.cricut.androidassessment.data.model.answer.Answer
import com.cricut.androidassessment.data.model.answer.MultipleChoiceAnswer
import com.cricut.androidassessment.data.model.answer.TrueFalseAnswer
import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.Question
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.data.model.results.AssessmentResult
import com.cricut.androidassessment.data.model.results.AssessmentResults
import com.cricut.androidassessment.data.repository.AssessmentRepository
import com.cricut.androidassessment.data.resultgenerator.ResultGenerator
import com.cricut.androidassessment.ui.screens.assessment.reducers.AnswerReducer
import com.cricut.androidassessment.ui.screens.assessment.reducers.AssessmentStateReducer
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class AssessmentViewModelTest {

    private lateinit var objectUnderTest: AssessmentViewModel

    private val mockAssessmentRepository: AssessmentRepository = mock()
    private val mockAnswerReducer: AnswerReducer = mock()
    private val reducerSpy = spy(AssessmentStateReducer(mockAnswerReducer))
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        objectUnderTest = AssessmentViewModel(mockAssessmentRepository, reducerSpy)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchQuestions calls correct methods`() = testScope.runTest {
        val questions = questions
        whenever(mockAssessmentRepository.getQuestions()).thenReturn(questions)
        objectUnderTest.fetchQuestions()
        advanceUntilIdle()

        val currentState = objectUnderTest.latestModel
        assertEquals(questions, currentState.questions)
        assertFalse(currentState.isLoading)
        verify(mockAssessmentRepository, times(1)).getQuestions()
        verify(reducerSpy, times(1)).updateStateWithQuestions(any(), eq(questions))
    }

    @Test
    fun `getAssessmentResults calls correct methods`() = testScope.runTest {
        val questions = questions
        val answers = answers
        objectUnderTest.mutableModel.update { it.copy(questions = questions, answers = answers) }
        val assessmentResults = AssessmentResults(
            score = 0.5f,
            listOf()
        )
        whenever(mockAssessmentRepository.getAssessmentResults(questions, answers)).thenReturn(
            assessmentResults
        )
        objectUnderTest.getAssessmentResults()
        advanceUntilIdle()

        verify(reducerSpy, times(1)).updateStateWithFetchingResults(any())
        verify(mockAssessmentRepository, times(1)).getAssessmentResults(eq(questions), eq(answers))
        verify(reducerSpy, times(1)).updateStateWithResults(any(), eq(assessmentResults))
    }

    @Test
    fun `onNextClicked calls correct reducer method`() = testScope.runTest {
        objectUnderTest.onNextClicked()
        advanceUntilIdle()
        verify(reducerSpy, times(1)).updateStateWithIsBusy(any())
        verify(reducerSpy, times(1)).updateStateWithNextQuestion(any())
    }

    @Test
    fun `onPreviousClicked calls correct reducer method`() {
        objectUnderTest.onPreviousQuestionClicked()
        verify(reducerSpy, times(1)).updateStateWithPreviousQuestion(any())
    }

    @Test
    fun `onAnswerValueChanged calls correct reducer method`() {
        val question = questions[0]
        val value = true
        objectUnderTest.onAnswerValueChanged(question, value)
        verify(reducerSpy, times(1)).updateAnswersWithValue(any(), eq(question), eq(value))
    }

    @Test
    fun `resetState calls correct reducer method`() {
        objectUnderTest.resetState()
        // This should be called twice, the first call happens in the init block
        verify(reducerSpy, times(2)).createInitialState()
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

    private val answers: Map<String, Answer>
        get() = mapOf(
            "1" to TrueFalseAnswer(
                questionId = "1",
                questionType = QuestionType.TrueFalse,
                answer = true
            ),
            "2" to MultipleChoiceAnswer(
                questionId = "2",
                questionType = QuestionType.MultiAnswerMultipleChoice,
                selectedIndices = setOf(1, 2)
            )
        )

}