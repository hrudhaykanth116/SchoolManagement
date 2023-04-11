package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate

data class ShortAnswerUIState(
    val option: Option,
    override val questionId: String,
) : AnswerUIState(questionId) {

    data class Option(
        val id: Int,
        val description: String? = null,
    )

    override val filterName: String = "Short answer"

    override fun clear(): AnswerUIState {
        val newOption = this.option.copy(
            description = ""
        )
        return this.copy(
            option = newOption
        )
    }

}