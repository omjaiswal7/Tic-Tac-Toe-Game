package com.example.tictactoe.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoe.data.CellState
import com.example.tictactoe.data.GameStatus
import com.example.tictactoe.data.Player
import kotlin.collections.mutableListOf

class GameViewModel : ViewModel() {

    var board = mutableStateListOf(
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY)
    )
        private set

    var currentPlayer = mutableStateOf(Player.X)
        private set

    var message = mutableStateOf("Turn X")
        private set

    var status = mutableStateOf(GameStatus.ONGOING)
        private set

    var winningCells = mutableStateListOf<Pair<Int, Int>>()
        private set

    fun makeMove(row: Int, col: Int) {

        if (status.value != GameStatus.ONGOING) return
        if (board[row][col] != CellState.EMPTY) return

        // This update will now trigger a UI refresh
        board[row][col] = if (currentPlayer.value == Player.X) CellState.X else CellState.O

        val winner = checkWinner()
        if(winner != null) {
            status.value = if(winner == Player.X) GameStatus.X_WON else GameStatus.O_WON
            message.value = "Player ${winner.name} Won!"
            return
        }

        val draw = isBoardFull()
        if(draw) {
            status.value = GameStatus.DRAW
            message.value = "It's a Draw!"
            return
        }

        currentPlayer.value = if (currentPlayer.value == Player.X) Player.O else Player.X
        message.value = "Turn: ${currentPlayer.value.name}"

    }

    fun restartGame() {
        for (row in 0..2) {
            for (col in 0..2) {
                board[row][col] = CellState.EMPTY
            }
        }

        currentPlayer.value = Player.X
        status.value = GameStatus.ONGOING
        message.value = "Turn X"
        winningCells.clear()


    }

    private fun checkWinner(): Player? {

        // Check Rows
        for(row in 0..2) {
            if(board[row][0] != CellState.EMPTY && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                winningCells.addAll(
                    listOf(Pair(row, 0), Pair(row, 1), Pair(row, 2))
                )
                return if(board[row][0] == CellState.X) Player.X else Player.O
            }
        }

        // Check columns
        for(col in 0..2) {
            if(board[0][col] != CellState.EMPTY && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                winningCells.addAll(
                    listOf(Pair(0, col), Pair(1, col), Pair(2, col))
                )
                return if(board[0][col] == CellState.X) Player.X else Player.O
            }
        }

        // Check diagonal 01
        if (board[0][0] != CellState.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winningCells.addAll(
                listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2))
            )
            return if(board[0][0] == CellState.X) Player.X else Player.O
        }

        // Check diagonal 02
        if(board[0][2] != CellState.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            winningCells.addAll(
                listOf(Pair(0,2), Pair(1,1), Pair(2,0))
            )
            return if(board[0][2] == CellState.X) Player.X else Player.O
        }
        return null
    }

    private fun isBoardFull() : Boolean {
        return board.all { row ->
            row.all { cell ->
                cell != CellState.EMPTY
            }
        }
    }

}