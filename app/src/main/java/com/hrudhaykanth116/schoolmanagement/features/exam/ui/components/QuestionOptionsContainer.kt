package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.examanswers.EasyAnswerView

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
            MultipleAnswersOptions(
                optionState = optionState,
                onStateChanged = onAnswered
            )
        }
        is AnswerUIState.FillInTheBlank -> {
            FillInTheBlankAnswerView(optionState, onAnswered)
        }
        is AnswerUIState.ShortAnswer -> {
            ShortAnswerView(optionState, onAnswered)
        }
        is AnswerUIState.TrueFalse -> {
            TrueFalseOptions(optionState, onAnswered)
        }
        is AnswerUIState.Unknown -> {
            Text(text = "Answer type is unknown.")
        }
        is AnswerUIState.Easy -> {
            EasyAnswerView(optionState, onAnswered)
        }
    }
}