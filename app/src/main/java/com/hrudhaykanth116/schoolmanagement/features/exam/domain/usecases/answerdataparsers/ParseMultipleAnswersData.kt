package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseMultipleAnswersData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): AnswerUIState.MultipleAnswers {
        val optionsList = mutableListOf<AnswerUIState.MultipleAnswers.OptionUIState>()

        questionDetail.questionOptions?.forEachIndexed { index, questionOption ->

            val answerOption = if (questionOption!!.description?.isNotBlank() == true) {
                questionOption.description
            } else {
                // Adding \[ \] as per library suggestions to parse latex codes.
                "\\[${questionOption.optionFormula}\\]"
            }

            val optionUIState: AnswerUIState.MultipleAnswers.OptionUIState =
                AnswerUIState.MultipleAnswers.OptionUIState(
                    // TODO: need to use Alphabets A, B, C, D
                    "${index + 1}", answerOption ?: "",
                    isSelected = false,
                )

            optionsList.add(optionUIState)
        }

        return AnswerUIState.MultipleAnswers(optionsList, questionDetail.questionId?.toString()!!)
    }

}