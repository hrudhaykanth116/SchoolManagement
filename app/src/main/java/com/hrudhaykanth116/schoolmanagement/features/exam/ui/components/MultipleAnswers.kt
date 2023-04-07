package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState


@Composable
fun MultipleAnswers(
    optionState: AnswerUIState.MultipleAnswers,
    modifier: Modifier,
) {

    optionState.optionsList


    Column() {
        optionState.optionsList.forEach { option ->
            AnswerOptionsView(
                modifier = modifier,
                isSelected = option.isSelected,
                index = option.index,
                content = option.content
            ){

            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}