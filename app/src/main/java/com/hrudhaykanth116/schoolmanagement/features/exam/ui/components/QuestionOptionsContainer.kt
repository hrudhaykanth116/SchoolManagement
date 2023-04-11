package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.*
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype.EasyAnswerView
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype.FillInTheBlankAnswerView
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype.MultipleAnswersUI
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype.MultipleChoiceAnswers
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype.ShortAnswerOptionsUI
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype.TrueFalseOptionsUI

@Composable
fun QuestionOptionsContainer(
    optionState: AnswerUIState,
    onAnswered: (AnswerUIState) -> Unit = {}
) {

    when (optionState) {
        is MultipleChoicesAnswerUIState -> {
            MultipleChoiceAnswers(
                optionState = optionState,
                onStateChanged = onAnswered
            )
        }
        is MultipleAnswersUIState -> {
            MultipleAnswersUI(
                optionState = optionState,
                onStateChanged = onAnswered
            )
        }
        is FillInTheBlankAnswerUIState -> {
            FillInTheBlankAnswerView(optionState, onAnswered)
        }
        is ShortAnswerUIState -> {
            ShortAnswerOptionsUI(optionState, onAnswered)
        }
        is TrueFalseAnswerUIState -> {
            TrueFalseOptionsUI(optionState, onAnswered)
        }
        is UnknownAnswerUIState -> {
            Text(text = "Answer type is unknown.")
        }
        is EasyAnswerUIState -> {
            EasyAnswerView(optionState, onAnswered)
        }
    }
}