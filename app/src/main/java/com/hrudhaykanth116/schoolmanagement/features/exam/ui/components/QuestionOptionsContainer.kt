package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState
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
        is AnswerUIState.MultipleChoicesUIState -> {
            MultipleChoiceAnswers(
                optionState = optionState,
                onStateChanged = onAnswered
            )
        }
        is AnswerUIState.MultipleAnswers -> {
            MultipleAnswersUI(
                optionState = optionState,
                onStateChanged = onAnswered
            )
        }
        is AnswerUIState.FillInTheBlank -> {
            FillInTheBlankAnswerView(optionState, onAnswered)
        }
        is AnswerUIState.ShortAnswer -> {
            ShortAnswerOptionsUI(optionState, onAnswered)
        }
        is AnswerUIState.TrueFalse -> {
            TrueFalseOptionsUI(optionState, onAnswered)
        }
        is AnswerUIState.Unknown -> {
            Text(text = "Answer type is unknown.")
        }
        is AnswerUIState.Easy -> {
            EasyAnswerView(optionState, onAnswered)
        }
    }
}