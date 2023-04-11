package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate

data class TrueFalseAnswerUIState(
    val optionList: List<Option>,
    override val questionId: String,
) : AnswerUIState(questionId) {

    data class Option(
        val id: Int,
        val content: String,
        val isSelected: Boolean,
    )

    override val filterName: String = "True False"

    override fun clear(): AnswerUIState {
        val newOptionsList = this.optionList.map {
            it.copy(
                isSelected = false
            )
        }
        return this.copy(
            optionList = newOptionsList
        )
    }
}