package com.example.tictactoe.gameui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import com.example.tictactoe.data.CellState


@Composable
fun Board(
    board: List<MutableList<CellState>>,
    winningCells: List<Pair<Int, Int>>,
    onCellClick: (row: Int, col: Int) -> Unit
) {

    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    // Calculating a responsive board size
    val side = (screenWidthDp - 64.dp).coerceAtMost(380.dp)

    Column(
        modifier = Modifier
            .size(side)
            .clip(RoundedCornerShape(20.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (row in 0..2) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                for (col in 0..2) {
                    Cell(
                        state = board[row][col],
                        onClick = { onCellClick(row, col) },
                        // Checks if this specific coordinate is part of the win
                        highLight = winningCells.contains(Pair(row, col)),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

}