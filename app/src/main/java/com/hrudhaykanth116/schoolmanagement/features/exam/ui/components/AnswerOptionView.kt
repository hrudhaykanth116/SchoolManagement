package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerData
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionOptions

@Composable
fun AnswerOptionView(
    modifier: Modifier = Modifier,
    option: QuestionOptions.MultipleChoices.Option,
    isSelected: Boolean,
    onAnswered: (AnswerData) -> Unit
) {


    Row(
        // Math renderer view needs more height
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = if (isSelected) Color.Green else Color.Black)
            // .padding(8.dp)
            .clickable {
                onAnswered(option.answerData)
            }
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Giving extra space for background circle to display properly for now.
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = option.index,
            modifier = Modifier
                // .padding(start = 2.dp, end = 2.dp)
                .drawBehind {
                    drawCircle(
                        color = if (isSelected) Color.Green else Color.DarkGray,
                        // TODO: Set the radius with size calculation.
                        radius = this.size.maxDimension
                    )
                },
            fontSize = 14.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.width(16.dp))
        MyMathComposable(modifier, option.content)
    }

}