package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

sealed interface QuestionsScreenEvent{
    object Next: QuestionsScreenEvent
    object Prev: QuestionsScreenEvent
    object FilterIconClicked: QuestionsScreenEvent
    data class Filter(val filterOptionsState: FilterOptionsState?): QuestionsScreenEvent
    data class Answered(
        val answerData: AnswerData
    ): QuestionsScreenEvent
}