package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import kotlinx.parcelize.Parcelize

sealed class AnswerType(
    val filterName: String,
) {

    data class MultipleChoices(
        val optionsList: List<Option>
    ): AnswerType("Multiple choice"){

        data class Option(
            val index: String,
            val content: String,
            val answerData: AnswerData,
        )

    }

    object Unknown: AnswerType("Unknown")

}