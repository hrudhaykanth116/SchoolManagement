package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState

@Composable
fun QuestionContainer(
    modifier: Modifier = Modifier,
    answersInitialUIState: AnswerUIState,
    onNextClicked: () -> Unit = {},
    onAnswered: (AnswerUIState) -> Unit = {}
) {

    // var answerUIState: AnswerUIState by remember {
    //     mutableStateOf(answersInitialUIState)
    // }

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
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                onNextClicked()
            }
        ) {
            Text(text = "Next")
        }
    }

}