package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

sealed class AnswerUIState(
    open val questionId: String,
) {
    fun getFilterName(): String {
        return when (this) {
            is FillInTheBlank -> "Fill in the blanks"
            is MultipleAnswers -> "Multiple answers"
            is MultipleChoicesUIState -> "Multiple choices"
            is ShortAnswer -> "Short answer"
            is TrueFalse -> "True False"
            is Easy -> "Easy"
            is Unknown -> "Unknown"
        }
    }

    data class MultipleChoicesUIState(
        val optionsList: List<OptionUIState>,
        override val questionId: String,
    ) : AnswerUIState(questionId) {

        data class OptionUIState(
            val index: String,
            val content: String,
            val isSelected: Boolean,
        )

    }

    data class MultipleAnswers(
        val optionsList: List<OptionUIState>,
        override val questionId: String,
    ) : AnswerUIState(questionId) {
        data class OptionUIState(
            val index: String,
            val content: String,
            val isSelected: Boolean,
        )
    }

    data class FillInTheBlank(
        val option: Option,
        override val questionId: String,
    ) : AnswerUIState(questionId) {

        data class Option(
            val id: Int,
            val description: String? = null,
        )

    }

    data class ShortAnswer(
        val option: Option,
        override val questionId: String,
    ) : AnswerUIState(questionId) {

        data class Option(
            val id: Int,
            val description: String? = null,
        )

    }

    // TODO: not sure on this case
    data class Easy(
        val option: Option,
        override val questionId: String,
    ) : AnswerUIState(questionId) {
        data class Option(
            val id: Int,
            val description: String? = null,
        )
    }

    data class TrueFalse(
        val optionList: List<Option>,
        override val questionId: String,
    ) : AnswerUIState(questionId) {
        data class Option(
            val id: Int,
            val content: String,
            val isSelected: Boolean,
        )
    }

    data class Unknown(
        override val questionId: String,
    ) : AnswerUIState(questionId)

}