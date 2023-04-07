package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.data.repository.exam.IExamRepository
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.ExamUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionUIState
import javax.inject.Inject

class GetExamUIStateUseCase @Inject constructor(
    private val repository: IExamRepository,
    private val parseExamDtoUseCase: ParseExamDtoUseCase
) {

    // private val examData: GetExamDataResponse.Result? = null
    //
    // private val examUIState: ExamUIState

    suspend operator fun invoke(currentExamUIState:  ExamUIState): ExamUIState {
        // examData?.let { dataResult ->
        //     // Do not make network/db call if data is already there because the
        //     // complete exam data would be fetched only once.
        //     return getQuestionUIStateUseCase(dataResult, questionsUIState)
        // }

        return when (val getExamDataResult = repository.getExamData()) {
            is DataResult.Error -> {
                currentExamUIState.copy(
                    errorMessage = getExamDataResult.uiMessage
                )
            }
            is DataResult.Success -> {
                // Considering only Happy path. No nulls or data type errors
                val examData: GetExamDataResponse.Result? = getExamDataResult.data.result!!

                if (examData != null) {
                    val examUIState = parseExamDtoUseCase(examData)
                    return examUIState
                } else {
                    return currentExamUIState.copy(
                        errorMessage = UIText.Text("Empty data")
                    )
                }
            }
        }
    }

}