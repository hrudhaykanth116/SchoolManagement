package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype

import androidx.compose.foundation.layout.Column
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState

@Composable
fun TrueFalseOptionsUI(
    uiState: AnswerUIState.TrueFalse,
    onAnswered: (AnswerUIState) -> Unit
) {

    val optionList = uiState.optionList

    Column(

    ) {
        optionList.forEach { option: AnswerUIState.TrueFalse.Option ->
            RadioButton(selected = option.isSelected, onClick = {

                val newOptionsUIState = optionList.map {
                    if (it == option) {
                        it.copy(
                            isSelected = true
                        )
                    } else {
                        it.copy(
                            isSelected = false
                        )
                    }
                }

                onAnswered(
                    uiState.copy(
                        optionList = newOptionsUIState
                    )
                )
            })
        }
    }


}