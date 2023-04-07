package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseTrueFalseAnswersData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): AnswerUIState.TrueFalse {

        val optionsList: MutableList<AnswerUIState.TrueFalse.Option> = mutableListOf()

        questionDetail.questionOptions?.forEach {
            val option: AnswerUIState.TrueFalse.Option = AnswerUIState.TrueFalse.Option(
                id = it?.optionId!!,
                content = it.description ?: "NA",
                isSelected = false,
            )
            optionsList.add(option)
        }

        return AnswerUIState.TrueFalse(
            questionId = questionDetail.questionId?.toString()!!,
            optionList = optionsList
        )
    }

}