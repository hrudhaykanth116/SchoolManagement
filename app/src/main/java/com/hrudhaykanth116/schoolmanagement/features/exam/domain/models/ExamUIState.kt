package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText

// TODO: Use Loading, Error, Success states for better state management
data class ExamUIState(
    val currentQuestionNumber: Int = 1,
    val currentQuestionUIState: QuestionUIState? = null,
    val questionsList: List<QuestionUIState>? = null,
    val filterList: List<FilterOption>? = null,
    val errorMessage: UIText? = null,
    val isLoading: Boolean = true,
)

// Handy extension function to get the question mapped to a question number.
fun List<QuestionUIState>?.getQuestion(questionNumber: Int): QuestionUIState? {
    return this?.firstOrNull{
        it.questionNumber == questionNumber
    }
}

// TODO: Move to other class
data class FilterOption(
    val name: String,
    val count: String,
)
