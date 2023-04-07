package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerData
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseMultipleChoiceData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): AnswerType.MultipleChoices {
        val optionsList = mutableListOf<AnswerType.MultipleChoices.Option>()

        questionDetail.questionOptions?.forEachIndexed { index, questionOption ->

            val answerOption = if (questionOption!!.description?.isNotBlank() == true) {
                questionOption.description
            } else {
                // Adding \[ \] as per library suggestions to parse latex codes.
                "\\[${questionOption.optionFormula}\\]"
            }

            val option: AnswerType.MultipleChoices.Option =
                AnswerType.MultipleChoices.Option(
                    "$index", answerOption ?: "",
                    // Better handle null cases if.
                    AnswerData(
                        optionId = questionOption.optionId ?: -1,
                        description = questionOption.description.orEmpty(),
                        formulae = questionOption.optionFormula.orEmpty(),
                    )
                )

            optionsList.add(option)
        }

        return AnswerType.MultipleChoices(optionsList)
    }

}