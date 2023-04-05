package com.hrudhaykanth116.schoolmanagement.features.exam.ui.screens.questions

import androidx.lifecycle.viewModelScope
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.GetQuestionsUseCase
import com.hrudhaykanth116.schoolmanagement.common.udf.UDFViewModel
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.getQuestion
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.GetExamUIStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEffect as Effect
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEvent as Event
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.ExamUIState as State

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val getExamUIStateUseCase: GetExamUIStateUseCase,
) : UDFViewModel<State, Event, Effect>(
    State()
) {

    var refreshDataJob: Job? = null

    init {
        refreshCurrentState()
    }

    override fun processEvent(event: Event) {
        when (event) {
            Event.Next -> {
                viewModelScope.launch {
                    val nextQuestionNumber = state.currentQuestionNumber + 1
                    setQuestionNumber(nextQuestionNumber)
                }
            }
            Event.Prev -> {
                viewModelScope.launch {
                    val prevQuestionNumber = state.currentQuestionNumber - 1
                    setQuestionNumber(prevQuestionNumber)
                }
            }
            is Event.Answered -> {

                val currentQuestionUIState = state.currentQuestionUIState
                val newQuestionUIState = currentQuestionUIState?.copy(
                    answerData = event.answerData
                )

                val currentQuestionsList = state.questionsList
                currentQuestionsList?.find {
                    it.questionNumber == currentQuestionUIState?.questionNumber
                }

                setState {
                    copy(
                        currentQuestionUIState = newQuestionUIState,
                        questionsList = currentQuestionsList
                    )
                }
            }
        }
    }

    /**
     * Sets the question number to the state and subsequently refreshes the ui state
     */
    private suspend fun setQuestionNumber(questionNumber: Int) {

        setState {
            copy(
                currentQuestionNumber = questionNumber,
                currentQuestionUIState = state.questionsList?.getQuestion(questionNumber),
            )
        }
    }

    private fun refreshCurrentState(){

        setState {
            copy(
                isLoading = true
            )
        }
        refreshDataJob?.cancel()
        refreshDataJob = viewModelScope.launch {

            val newExamUIState = getExamUIStateUseCase(state)

            setState {
                // Whenever refresh happens reset data to initial state.
                newExamUIState.copy(
                    isLoading = false,
                    currentQuestionNumber = 1,
                    currentQuestionUIState = newExamUIState.questionsList?.getQuestion(1),
                )
            }
        }

    }

}