package com.app.pokedex.presentation.view.HomePokedex

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.app.pokedex.ui.theme.circleColor

@Composable
fun CircleTopPokedex() {
    Canvas(modifier = Modifier
            .padding(horizontal = 5.dp)
            .padding(top = 40.dp)
            .size(100.dp)
    ) {
            val canvasWidth = size.width
            val crossSize = canvasWidth * 0.5f

            drawRoundRect(
                color = circleColor,
                topLeft = Offset(40f, -24f),
                size = Size(crossSize + 40, crossSize + 40),
                cornerRadius = CornerRadius(100.dp.toPx())
            )
            drawRoundRect(
                color = Color.White.copy(alpha = 0.8f),
                topLeft = Offset( 40f, -24f),
                size = Size(crossSize + 40, crossSize + 40),
                cornerRadius = CornerRadius(100.dp.toPx()),
                style = Stroke(width = 9.dp.toPx())
            )

            drawRoundRect(
                color = Color(0xFF83CDFE),
                topLeft = Offset(70f, 0f),
                size = Size(56f, 56f),
                cornerRadius = CornerRadius(100.dp.toPx())
            )
        }
}