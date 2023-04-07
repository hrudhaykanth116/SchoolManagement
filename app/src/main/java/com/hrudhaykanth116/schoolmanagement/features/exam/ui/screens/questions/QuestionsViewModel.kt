package com.hrudhaykanth116.schoolmanagement.features.exam.ui.screens.questions

import androidx.lifecycle.viewModelScope
import com.hrudhaykanth116.schoolmanagement.common.udf.UDFViewModel
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOptionsState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.getQuestion
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.GetExamUIStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEffect as Effect
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEvent as Event
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.ExamUIState as State

@HiltViewModel
class QuestionsViewModel @Inject constructor(
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

                val nextQuestionNumber = state.currentQuestionNumber + 1

                if(nextQuestionNumber <= state.filteredQuestionList.size){
                    setQuestionNumber(nextQuestionNumber)
                }else{
                    // TODO: Stop next May be show submit button
                }

            }
            Event.Prev -> {
                val prevQuestionNumber = state.currentQuestionNumber - 1
                setQuestionNumber(prevQuestionNumber)
            }
            is Event.Answered -> {
                onAnswered(event)
            }
            Event.FilterIconClicked -> {
                setState {
                    copy(
                        isInFilterMode = true,
                    )
                }
            }
            is Event.Filter -> {
                performFilter(event.filterOptionsState)
            }
        }
    }

    private fun performFilter(newFilterOptionState: FilterOptionsState?) {
        val filteredOptions = newFilterOptionState?.filterOptionsList?.filter {
            it.isChecked
        } ?: listOf()

        val filteredQuestionsList: List<QuestionUIState> =
            state.questionsList.filter { questionUIState ->
                filteredOptions.any { filteredOption ->
                    questionUIState.subject == filteredOption.name
                }
            }

        // TODO: Exit filter mode properly with an event.
        setState {
            copy(
                isInFilterMode = false,
                filterOptionsState = newFilterOptionState,
                filteredQuestionList = filteredQuestionsList,
                currentQuestionNumber = 1,
                // TODO: After filter happens start from beginning. Check this behavior
                currentQuestionUIState = state.filteredQuestionList?.getQuestion(1),
            )
        }
    }

    private fun onAnswered(event: com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEvent.Answered) {
        val currentQuestionUIState = state.currentQuestionUIState
        val newQuestionUIState = currentQuestionUIState?.copy(
            answerData = event.answerData
        )

        val newQuestionsList = state.filteredQuestionList
        newQuestionsList?.find {
            it.questionNumber == currentQuestionUIState?.questionNumber
        }

        setState {
            copy(
                currentQuestionUIState = newQuestionUIState,
                filteredQuestionList = newQuestionsList
            )
        }
    }

    /**
     * Sets the question number to the state and subsequently refreshes the ui state
     */
    private fun setQuestionNumber(questionNumber: Int) {

        setState {
            copy(
                currentQuestionNumber = questionNumber,
                currentQuestionUIState = state.filteredQuestionList?.getQuestion(questionNumber),
            )
        }
    }

    private fun refreshCurrentState() {

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
                    currentQuestionUIState = newExamUIState.filteredQuestionList?.getQuestion(1),
                )
            }
        }

    }

}