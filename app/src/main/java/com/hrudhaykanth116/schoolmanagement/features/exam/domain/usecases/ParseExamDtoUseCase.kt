package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.ExamUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionUIState
import javax.inject.Inject

class ParseExamDtoUseCase @Inject constructor(
    private val getQuestionUIStateUseCase: GetQuestionUIStateUseCase
) {

    suspend operator fun invoke(examData: GetExamDataResponse.Result): ExamUIState {


        val questionsUIState = mutableListOf<QuestionUIState>()
        examData.questionDetails!!.forEachIndexed { index, questionDetail: GetExamDataResponse.Result.QuestionDetail? ->
            val questionUIState: QuestionUIState = getQuestionUIStateUseCase(questionDetail).copy(
                // 0th question in list will be the first question on UI
                questionNumber = index + 1
            )
            // No index or sorting data. So, assuming the order from backend.
            questionsUIState.add(questionUIState)
        }

        return ExamUIState(
            questionsList = questionsUIState,
        )


    }

}