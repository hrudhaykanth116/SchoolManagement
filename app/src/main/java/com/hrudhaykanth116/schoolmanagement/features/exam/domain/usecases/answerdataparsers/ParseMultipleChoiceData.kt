package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseMultipleChoiceData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): AnswerUIState.MultipleChoicesUIState {
        val optionsList = mutableListOf<AnswerUIState.MultipleChoicesUIState.OptionUIState>()

        questionDetail.questionOptions?.forEachIndexed { index, questionOption ->

            val answerOption = if (questionOption!!.description?.isNotBlank() == true) {
                questionOption.description
            } else {
                // Adding \[ \] as per library suggestions to parse latex codes.
                "\\[${questionOption.optionFormula}\\]"
            }

            val optionUIState: AnswerUIState.MultipleChoicesUIState.OptionUIState =
                AnswerUIState.MultipleChoicesUIState.OptionUIState(
                    // TODO: need to use Alphabets A, B, C, D
                    "${index + 1}", answerOption ?: "",
                    isSelected = false,
                )

            optionsList.add(optionUIState)
        }

        return AnswerUIState.MultipleChoicesUIState(optionsList, questionDetail.questionId?.toString()!!)
    }

}