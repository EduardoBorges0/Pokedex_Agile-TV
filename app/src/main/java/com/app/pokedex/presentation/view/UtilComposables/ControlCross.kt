package com.app.pokedex.presentation.view.UtilComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ClickableControlCross(
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit,
    onDownClick: () -> Unit,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    val widthDp = LocalConfiguration.current.screenWidthDp / 4

    Box(
        modifier = modifier.size(widthDp.dp + 30.dp),
        contentAlignment = Alignment.Center
    ) {
        // Botão para cima
        Box(
            modifier = Modifier
                .size(widthDp.dp * 0.5f, widthDp.dp * 0.75f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .clickable { onUpClick() }
                .align(Alignment.TopCenter)
        )

        // Botão para baixo
        Box(
            modifier = Modifier
                .size(widthDp.dp * 0.5f, widthDp.dp * 0.75f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .clickable { onDownClick() }
                .align(Alignment.BottomCenter)
        )

        // Botão para a esquerda
        Box(
            modifier = Modifier
                .size(widthDp.dp * 0.75f, widthDp.dp * 0.5f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .clickable { onLeftClick() }
                .align(Alignment.CenterStart)
        )

        // Botão para a direita
        Box(
            modifier = Modifier
                .size(widthDp.dp * 0.75f, widthDp.dp * 0.5f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .clickable { onRightClick() }
                .align(Alignment.CenterEnd)
        )
    }
}
