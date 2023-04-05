package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

sealed interface QuestionOptions {

    data class MultipleChoices(
        val optionsList: List<Option>
    ): QuestionOptions{

        data class Option(
            val index: String,
            val content: String,
            val answerData: AnswerData,
        )

    }

}