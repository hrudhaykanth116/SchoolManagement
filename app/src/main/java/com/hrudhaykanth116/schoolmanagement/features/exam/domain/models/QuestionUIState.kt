package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText

data class QuestionUIState(
    val questionId: String? = null,
    val questionNumber: Int = 1,
    val question: String? = null,
    val questionTitle: UIText? = null,
    val answerTitle: UIText? = null,
    val errorMessage: UIText? = null,
    val answerUIState: AnswerUIState = AnswerUIState.Unknown("NA"),
    val subject: String? = null,
)