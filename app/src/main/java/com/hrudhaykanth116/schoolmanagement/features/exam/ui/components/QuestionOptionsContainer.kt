package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerData
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionOptions

@Composable
fun QuestionOptionsContainer(
    modifier: Modifier = Modifier,
    optionState: QuestionOptions,
    answerData: AnswerData? = null,
    onAnswered: (AnswerData) -> Unit = {}
) {

    when (optionState) {
        is QuestionOptions.MultipleChoices -> {
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
    }
}

@Preview
@Composable
private fun QuestionOptionsContainerPreview() {
    QuestionOptionsContainer(
        optionState = QuestionOptions.MultipleChoices(
            listOf(
                // QuestionOptions.MultipleChoices.Option(
                //     "A",
                //     "a + b = c"
                // ),
                // QuestionOptions.MultipleChoices.Option(
                //     "A",
                //     "a + b = c"
                // ),
                // QuestionOptions.MultipleChoices.Option(
                //     "A",
                //     "a + b = c"
                // ),
                // QuestionOptions.MultipleChoices.Option(
                //     "A",
                //     "a + b = c"
                // )
            )
        )
    )
}
