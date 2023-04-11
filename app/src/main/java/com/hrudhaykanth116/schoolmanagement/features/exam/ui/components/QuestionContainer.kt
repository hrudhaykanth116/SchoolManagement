package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.AnswerUIState

@Composable
fun QuestionContainer(
    modifier: Modifier = Modifier,
    answersInitialUIState: AnswerUIState,
    onNextClicked: () -> Unit = {},
    onAnswered: (AnswerUIState) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.weight(1f)) {
            QuestionOptionsContainer(
                optionState = answersInitialUIState,
                onAnswered = onAnswered
            )
        }
        // Button(
        //     modifier = Modifier.align(Alignment.End),
        //     onClick = {
        //         onNextClicked()
        //     }
        // ) {
        //     Text(text = "Next")
        // }
    }

}