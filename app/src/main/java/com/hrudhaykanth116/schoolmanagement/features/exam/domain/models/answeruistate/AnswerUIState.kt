package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate

sealed class AnswerUIState(
    open val questionId: String,
) {

    abstract val filterName: String

    abstract fun clear(): AnswerUIState

}