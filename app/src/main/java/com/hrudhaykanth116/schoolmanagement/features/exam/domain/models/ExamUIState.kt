package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText

// TODO: Use Loading, Error, Success states for better state management
data class ExamUIState(
    val currentQuestionNumber: Int = 1,
    val currentQuestionUIState: QuestionUIState = QuestionUIState(),
    val questionsList: List<QuestionUIState> = listOf(),
    val questionsToDisplayList: List<QuestionUIState> = listOf(),
    val filterOptionsState: FilterOptionsState? = null,
    val errorMessage: UIText? = null,
    val isLoading: Boolean = true,
    val isInFilterMode: Boolean = false
)

// Handy extension function to get the question mapped to a question number.
fun List<QuestionUIState>?.getQuestionUIState(questionNumber: Int): QuestionUIState {
    return this?.getOrNull(questionNumber - 1) ?: QuestionUIState()
    // return this?.firstOrNull{
    //     it.questionNumber == questionNumber
    // }
}

