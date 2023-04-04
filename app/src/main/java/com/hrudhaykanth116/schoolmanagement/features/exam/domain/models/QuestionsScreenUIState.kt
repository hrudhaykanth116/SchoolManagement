package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText

// TODO: Use Loading, Error, Success states for better state management
data class QuestionsScreenUIState(
    val questionNumber: Int = 1,
    val question: String? = null,
    val questionTitle: UIText? = null,
    val answerTitle: UIText? = null,
    val errorMessage: UIText? = null,
    val questionOptions: QuestionOptions? = null,
)