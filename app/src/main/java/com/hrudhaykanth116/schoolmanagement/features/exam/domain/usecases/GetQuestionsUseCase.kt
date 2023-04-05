package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.data.repository.exam.IExamRepository
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionUIState
import javax.inject.Inject

// Do not use singleton as this holds exam data state.
class GetQuestionsUseCase @Inject constructor(
    private val repository: IExamRepository,
    private val getQuestionUIStateUseCase: GetQuestionUIStateUseCase,
) {

    // TODO: Parse the whole json at once into uiState with selection state also
    //  instead of rendering UI state for each question. No need to cancel the api also in that case.
    private val examData: GetExamDataResponse.Result? = null

    suspend operator fun invoke(questionUIState: QuestionUIState): QuestionUIState {

        examData?.let { dataResult ->
            // Do not make network/db call if data is already there because the
            // complete exam data would be fetched only once.
            val questions: List<GetExamDataResponse.Result.QuestionDetail?> =
                dataResult.questionDetails!!

            val questionDetail = questions.getOrNull(
                // json array starts from 0. So, subtract 1 to get correctly mapped question.
                questionUIState.questionNumber - 1
            )!!
            // return getQuestionUIStateUseCase(questionDetail)
            return getQuestionState(
                questionUIState,
                dataResult
            )
        }

        return when (val getExamDataResult = repository.getExamData()) {
            is DataResult.Error -> {
                questionUIState.copy(
                    errorMessage = getExamDataResult.uiMessage
                )
            }
            is DataResult.Success -> {
                // Considering only Happy path. No nulls or data type errors
                return getQuestionState(
                    questionUIState,
                    getExamDataResult.data.result!!
                )
            }
        }
    }

    private suspend fun getQuestionState(
        questionUIState: QuestionUIState,
        questionDetails: GetExamDataResponse.Result
    ): QuestionUIState {
        val questions: List<GetExamDataResponse.Result.QuestionDetail?>? = questionDetails.questionDetails

        return if (questions != null) {
            val questionDetail = questions.getOrNull(
                // json array starts from 0. So, subtract 1 to get correctly mapped question.
                questionUIState.questionNumber - 1
            )!!
            getQuestionUIStateUseCase(questionDetail)
        } else {
            questionUIState.copy(
                errorMessage = UIText.Text("Empty data")
            )
        }
    }

}