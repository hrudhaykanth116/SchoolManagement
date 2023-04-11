package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate

data class MultipleAnswersUIState(
    val optionsList: List<OptionUIState>,
    override val questionId: String,
) : AnswerUIState(questionId) {

    data class OptionUIState(
        val index: String,
        val content: String,
        val isSelected: Boolean,
    )

    override val filterName: String = "Multiple answers"

    override fun clear(): AnswerUIState {
        val newOptionsList = optionsList.map {
            it.copy(
                isSelected = false
            )
        }

        return this.copy(
            optionsList = newOptionsList
        )
    }
}