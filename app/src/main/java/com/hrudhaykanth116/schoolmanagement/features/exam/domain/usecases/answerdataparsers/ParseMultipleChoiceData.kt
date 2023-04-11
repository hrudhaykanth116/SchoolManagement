package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.common.utils.integer.toAlphabet
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.MultipleChoicesAnswerUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseMultipleChoiceData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): MultipleChoicesAnswerUIState {
        val optionsList = mutableListOf<MultipleChoicesAnswerUIState.OptionUIState>()

        questionDetail.questionOptions?.forEachIndexed { index, questionOption ->

            val answerOption = if (questionOption!!.description?.isNotBlank() == true) {
                questionOption.description
            } else {
                // Adding \[ \] as per library suggestions to parse latex codes.
                "\\[${questionOption.optionFormula}\\]"
            }

            val optionUIState: MultipleChoicesAnswerUIState.OptionUIState =
                MultipleChoicesAnswerUIState.OptionUIState(
                    // TODO: need to use Alphabets A, B, C, D
                    "${(index + 1).toAlphabet()}", answerOption ?: "",
                    isSelected = false,
                )

            optionsList.add(optionUIState)
        }

        return MultipleChoicesAnswerUIState(optionsList, questionDetail.questionId?.toString()!!)
    }

}