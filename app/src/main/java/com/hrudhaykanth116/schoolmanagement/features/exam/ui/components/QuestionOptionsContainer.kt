package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerData
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerType

@Composable
fun QuestionOptionsContainer(
    modifier: Modifier = Modifier,
    optionState: AnswerType,
    answerData: AnswerData? = null,
    onAnswered: (AnswerData) -> Unit = {}
) {

    when (optionState) {
        is AnswerType.MultipleChoices -> {
            MultipleChoiceAnswers(optionState, modifier, answerData, onAnswered)
        }
        else -> {
            Text(text = "Answer type is unknown.")
        }
    }
}

@Preview
@Composable
private fun QuestionOptionsContainerPreview() {
    QuestionOptionsContainer(
        optionState = AnswerType.MultipleChoices(
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
