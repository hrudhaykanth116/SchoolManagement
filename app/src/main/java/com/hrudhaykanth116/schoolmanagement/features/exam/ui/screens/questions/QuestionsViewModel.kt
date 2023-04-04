package com.hrudhaykanth116.schoolmanagement.features.exam.ui.screens.questions

import androidx.lifecycle.viewModelScope
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.GetQuestionsUseCase
import com.hrudhaykanth116.schoolmanagement.common.udf.UDFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEffect as Effect
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEvent as Event
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenUIState as State

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
) : UDFViewModel<State, Event, Effect>(
    State()
) {

    init {
        viewModelScope.launch {
            val newState = getQuestionsUseCase(state)

            setState {
                newState
            }

        }
    }

    override fun processEvent(event: Event) {
        when (event) {
            Event.Next -> {
                viewModelScope.launch {
                    val nextQuestionNumber = state.questionNumber + 1
                    setQuestionNumber(nextQuestionNumber)
                }
            }
            Event.Prev -> {
                viewModelScope.launch {
                    val prevQuestionNumber = state.questionNumber - 1
                    setQuestionNumber(prevQuestionNumber)
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
                questionNumber = questionNumber
            )
        }

        val newState = getQuestionsUseCase(state)

        setState {
            newState
        }
    }

}