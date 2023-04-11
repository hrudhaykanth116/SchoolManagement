package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.TrueFalseAnswerUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseTrueFalseAnswersData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): TrueFalseAnswerUIState {

        val optionsList: MutableList<TrueFalseAnswerUIState.Option> = mutableListOf()

        questionDetail.questionOptions?.forEach {
            val option: TrueFalseAnswerUIState.Option = TrueFalseAnswerUIState.Option(
                id = it?.optionId!!,
                content = it.description ?: "NA",
                isSelected = false,
            )
            optionsList.add(option)
        }

        return TrueFalseAnswerUIState(
            questionId = questionDetail.questionId?.toString()!!,
            optionList = optionsList
        )
    }

}