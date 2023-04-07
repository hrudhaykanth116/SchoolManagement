package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerData
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerType


@Composable
fun MultipleChoiceAnswers(
    optionState: AnswerType.MultipleChoices,
    modifier: Modifier,
    answerData: AnswerData?,
    onAnswered: (AnswerData) -> Unit
) {
    Column() {
        optionState.optionsList.forEach { option ->
            AnswerOptionView(
                modifier,
                option,
                isSelected = option.answerData == answerData,
                onAnswered
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}