package com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.qdot.mathrendererlib.MathRenderView
import com.qdot.mathrendererlib.TextAlign

@Composable
fun MathFormulaUI(
    modifier: Modifier,
    text: String
) {
    AndroidView(
        modifier = modifier
            .fillMaxSize(),
        factory = { context ->
            MathRenderView(context).apply {
                this.text = text
                this.textAlignment = TextAlign.START
            }
        },
        update = {
            it.text = text
        }
    )
}