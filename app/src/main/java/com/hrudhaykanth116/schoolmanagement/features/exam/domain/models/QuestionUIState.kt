package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import com.hrudhaykanth116.schoolmanagement.common.ui.models.UIText
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.AnswerUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.UnknownAnswerUIState

data class QuestionUIState(
    val questionId: String? = null,
    val questionNumber: Int = 1,
    val question: String? = null,
    val questionTitle: UIText? = null,
    val answerTitle: UIText? = null,
    val errorMessage: UIText? = null,
    val answerUIState: AnswerUIState = UnknownAnswerUIState("NA"),
    val subject: String? = null,
)