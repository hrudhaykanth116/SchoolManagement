package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.common.utils.integer.toAlphabet
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.MultipleAnswersUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseMultipleAnswersData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): MultipleAnswersUIState {
        val optionsList = mutableListOf<MultipleAnswersUIState.OptionUIState>()

        questionDetail.questionOptions?.forEachIndexed { index, questionOption ->

            val answerOption = if (questionOption!!.description?.isNotBlank() == true) {
                questionOption.description
            } else {
                // Adding \[ \] as per library suggestions to parse latex codes.
                "\\[${questionOption.optionFormula}\\]"
            }

            val optionUIState: MultipleAnswersUIState.OptionUIState =
                MultipleAnswersUIState.OptionUIState(
                    // TODO: need to use Alphabets A, B, C, D
                    "${(index + 1).toAlphabet()}", answerOption ?: "",
                    isSelected = false,
                )

            optionsList.add(optionUIState)
        }

        return MultipleAnswersUIState(optionsList, questionDetail.questionId?.toString()!!)
    }

}