package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.data.repository.exam.IExamRepository
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

// Do not use singleton as this holds exam data state.
class GetQuestionsUseCase @Inject constructor(
    private val repository: IExamRepository,
    private val getQuestionUIStateUseCase: GetQuestionUIStateUseCase,
) {

    // TODO: Parse the whole json at once into uiState with selection state also
    //  instead of rendering UI state for each question. No need to cancel the api also in that case.
    private val examData: GetExamDataResponse.Result? = null

    suspend operator fun invoke(questionsScreenUIState: QuestionsScreenUIState): QuestionsScreenUIState {

        examData?.let { dataResult ->
            // Do not make network/db call if data is already there because the
            // complete exam data would be fetched only once.
            return getQuestionUIStateUseCase(dataResult, questionsScreenUIState)
        }

        return when (val getExamDataResult = repository.getExamData()) {
            is DataResult.Error -> {
                questionsScreenUIState.copy(
                    errorMessage = getExamDataResult.uiMessage
                )
            }
            is DataResult.Success -> {
                // Considering only Happy path. No nulls or data type errors
                val examData: GetExamDataResponse.Result? = getExamDataResult.data.result!!

                if (examData != null) {
                    return getQuestionUIStateUseCase(examData, questionsScreenUIState)

                } else {
                    return questionsScreenUIState.copy(
                        errorMessage = UIText.Text("Empty data")
                    )
                }
            }
        }
    }
}