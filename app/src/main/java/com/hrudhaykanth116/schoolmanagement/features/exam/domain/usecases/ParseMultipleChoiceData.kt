package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseMultipleChoiceData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): QuestionOptions.MultipleChoices {
        val optionsList = mutableListOf<QuestionOptions.MultipleChoices.Option>()

        questionDetail.questionOptions?.forEachIndexed { index, questionOption ->

            val answerOption = if (questionOption!!.description?.isNotBlank() == true) {
                questionOption.description
            } else {
                // Adding \[ \] as per library suggestions to parse latex codes.
                "\\[${questionOption.optionFormula}\\]"
            }

            val option: QuestionOptions.MultipleChoices.Option =
                QuestionOptions.MultipleChoices.Option("$index", answerOption ?: "")

            optionsList.add(option)
        }

        return QuestionOptions.MultipleChoices(optionsList)
    }

}