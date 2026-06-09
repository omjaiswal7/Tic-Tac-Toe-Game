package com.example.tictactoe.gameui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.sp
import com.example.tictactoe.data.CellState

@Composable
fun Cell(
    state: CellState,
    onClick: () -> Unit,
    highLight: Boolean,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(targetValue = if (state != CellState.EMPTY) 1f else 0.5f)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(6.dp)
            .clickable(
                enabled = state == CellState.EMPTY
            ) {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = if (highLight) Color(0xFFFFEB3B) else Color.White
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (state) {
                    CellState.X -> "X"
                    CellState.O -> "O"
                    else -> ""
                },
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = when (state) {
                    CellState.X -> Color(0xFF1976D2)
                    CellState.O -> Color(0xFFD32F2F)
                    else -> Color.Transparent
                },
                modifier = Modifier.scale(scale)
            )

        }

    }

}