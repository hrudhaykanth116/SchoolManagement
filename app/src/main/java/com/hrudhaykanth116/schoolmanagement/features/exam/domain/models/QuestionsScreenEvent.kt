package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

sealed interface QuestionsScreenEvent{
    object Next: QuestionsScreenEvent
    object Prev: QuestionsScreenEvent
}