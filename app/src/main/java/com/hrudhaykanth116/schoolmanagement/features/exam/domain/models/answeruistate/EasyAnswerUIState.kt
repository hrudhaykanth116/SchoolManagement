package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate

// TODO: not sure on this case
data class EasyAnswerUIState(
    val option: Option,
    override val questionId: String,
) : AnswerUIState(questionId) {
    data class Option(
        val id: Int,
        val description: String? = null,
    )

    override val filterName: String = "Easy"

    override fun clear(): AnswerUIState {
        val newOption = this.option.copy(
            description = ""
        )
        return this.copy(
            option = newOption
        )
    }
}