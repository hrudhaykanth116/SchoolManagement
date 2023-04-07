package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState


@Composable
fun MultipleAnswersOptions(
    modifier: Modifier = Modifier,
    optionState: AnswerUIState.MultipleAnswers,
    onStateChanged: (AnswerUIState.MultipleAnswers) -> Unit
) {


    val currentOptionsList = optionState.optionsList

    Column() {
        currentOptionsList.forEach { optionUIState: AnswerUIState.MultipleAnswers.OptionUIState ->
            AnswerOptionsView(
                modifier = modifier,
                index = optionUIState.index,
                isSelected = optionUIState.isSelected,
                content = optionUIState.content,
            ) {


                // val currentOptionState = optionState.optionsList.find { it == clickedOption }
                // val newOptionState = currentOptionState?.copy(
                //     isSelected = !currentOptionState.isSelected
                // )

                val newOptionsState = currentOptionsList.map {
                    if (it == optionUIState) {
                        it.copy(
                            isSelected = !it.isSelected
                        )
                    } else {
                        it.copy(
                            isSelected = false
                        )
                    }
                }

                val newAnswerUIState = optionState.copy(
                    optionsList = newOptionsState
                )

                onStateChanged(
                    newAnswerUIState
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}