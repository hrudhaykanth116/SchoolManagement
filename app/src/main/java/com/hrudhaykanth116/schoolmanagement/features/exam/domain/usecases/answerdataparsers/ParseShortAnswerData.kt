package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseShortAnswerData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): AnswerUIState.ShortAnswer {
        return AnswerUIState.ShortAnswer(
            questionId = questionDetail.questionId?.toString()!!,
            option = AnswerUIState.ShortAnswer.Option(
                id = questionDetail.questionOptions?.getOrNull(0)?.optionId!!
            )
        )
    }

}