package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionOptions
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.MyMathComposable

@Composable
fun AnswerOptionView(
    modifier: Modifier = Modifier,
    option: QuestionOptions.MultipleChoices.Option,
) {
    Row(
        // Math renderer view needs more height
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Black)
            .padding(8.dp)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = option.index,
            fontSize = 20.sp,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.width(8.dp))
        MyMathComposable(modifier, option.content)
    }

}