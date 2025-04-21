package com.cricut.androidassessment.ui.screens.common

import com.cricut.androidassessment.data.model.common.QuestionType
import com.cricut.androidassessment.data.model.question.MultiAnswerMultipleChoiceQuestion
import com.cricut.androidassessment.data.model.question.TrueFalseQuestion
import com.cricut.androidassessment.data.repository.AssessmentRepository
import com.cricut.androidassessment.data.resultgenerator.ResultGenerator
import com.cricut.androidassessment.ui.screens.assessment.reducers.AnswerReducer
import com.cricut.androidassessment.ui.screens.assessment.reducers.AssessmentStateReducer
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ThingDoer() {
    fun doThing() {
        print("thing done")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class AssessmentViewModelTest {

    @Test
    fun testMockSomething() {
        val mockThing: ThingDoer = mock()
        assertNotNull(mockThing)
    }

//    lateinit var objectUnderTest: AssessmentViewModel
//
//    private val mockAssessmentRepository: AssessmentRepository = mock()
//    private val mockAnswerReducer: AnswerReducer = mock()
//    private val reducerSpy = spy(AssessmentStateReducer(mockAnswerReducer))
//    private val testDispatcher = StandardTestDispatcher()
//    private val testScope = TestScope(testDispatcher)
//
//    @Before
//    fun setup() {
//        objectUnderTest = AssessmentViewModel(mockAssessmentRepository, reducerSpy)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun testFetchQuestions() = testScope.runTest {
//        val questions = listOf(
//            TrueFalseQuestion(
//                id = "1",
//                questionText = "Is this a question?",
//                questionType = QuestionType.TrueFalse,
//                correctAnswer = true
//            ),
//            MultiAnswerMultipleChoiceQuestion(
//                id = "2",
//                questionText = "Is this a question?",
//                questionType = QuestionType.MultiAnswerMultipleChoice,
//                correctAnswers = setOf(1, 2),
//                options = listOf("Option 1", "Option 2", "Option 3")
//            )
//        )
//        whenever(mockAssessmentRepository.getQuestions()).thenReturn(questions)
//        advanceUntilIdle()
//
//        val currentState = objectUnderTest.latestModel
//        assertEquals(questions, currentState.questions)
//        assertFalse(currentState.isLoading)
//        verify(mockAssessmentRepository, times(1)).getQuestions()
//        verify(reducerSpy, times(1)).updateStateWithQuestions(any(), questions)
//    }


}