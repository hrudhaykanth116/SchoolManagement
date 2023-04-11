package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.EasyAnswerUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseEasyAnswersData @Inject constructor() {

    /**
     * Extracts options list from given [GetExamDataResponse.Result.QuestionDetail] that can
     * be rendered on the UI
     */
    operator fun invoke(questionDetail: GetExamDataResponse.Result.QuestionDetail): EasyAnswerUIState {
        return EasyAnswerUIState(
            questionId = questionDetail.questionId?.toString()!!,
            option = EasyAnswerUIState.Option(
                id = questionDetail.questionOptions?.getOrNull(0)?.optionId!!,
                description = questionDetail.questionOptions.getOrNull(0)?.description
            )
        )
    }

}