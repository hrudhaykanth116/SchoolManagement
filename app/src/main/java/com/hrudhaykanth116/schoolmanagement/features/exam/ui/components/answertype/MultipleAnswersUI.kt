package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.answertype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.MultipleAnswersUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.common.AnswerOptionUI


@Composable
fun MultipleAnswersUI(
    modifier: Modifier = Modifier,
    optionState: MultipleAnswersUIState,
    onStateChanged: (MultipleAnswersUIState) -> Unit
) {


    val currentOptionsList = optionState.optionsList

    Column() {
        currentOptionsList.forEach { optionUIState: MultipleAnswersUIState.OptionUIState ->
            AnswerOptionUI(
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