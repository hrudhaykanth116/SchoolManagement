package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText

data class QuestionUIState(
    val questionNumber: Int = 1,
    val question: String? = null,
    val questionTitle: UIText? = null,
    val answerTitle: UIText? = null,
    val errorMessage: UIText? = null,
    val answerData: AnswerData? = null, // No answer given
    val answerType: AnswerType = AnswerType.Unknown,
    val subject: String? = null,
)