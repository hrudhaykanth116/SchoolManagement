package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate

data class UnknownAnswerUIState(
    override val questionId: String,
) : AnswerUIState(questionId){

    override val filterName: String = "Unknown"

    override fun clear(): AnswerUIState {
        // Nothing to clear
        return this
    }

}