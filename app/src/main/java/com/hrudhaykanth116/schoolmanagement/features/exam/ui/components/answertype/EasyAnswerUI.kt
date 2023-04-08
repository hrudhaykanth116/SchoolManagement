package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState

@Composable
fun EasyAnswerView(
    optionState: AnswerUIState.Easy,
    onAnswered: (AnswerUIState) -> Unit
) {
    OutlinedTextField(value = optionState.option.description ?: "", onValueChange = {
        onAnswered(
            optionState.copy(
                option = optionState.option.copy(
                    description = it
                )
            )
        )
    })
}