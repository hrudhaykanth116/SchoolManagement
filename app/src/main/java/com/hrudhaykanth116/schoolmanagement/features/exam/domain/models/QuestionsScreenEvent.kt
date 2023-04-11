package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.AnswerUIState

sealed interface QuestionsScreenEvent{
    object Next: QuestionsScreenEvent
    object Prev: QuestionsScreenEvent
    object FilterIconClicked: QuestionsScreenEvent
    object Clear: QuestionsScreenEvent
    data class Filter(val filterOptionsState: FilterOptionsState?): QuestionsScreenEvent
    data class AnswerStateChanged(
        val answerUIState: AnswerUIState
    ): QuestionsScreenEvent
}