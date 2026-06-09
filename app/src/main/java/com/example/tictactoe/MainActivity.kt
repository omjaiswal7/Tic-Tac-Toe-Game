package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe.gameui.TicTacTowGame
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
// Future Improvements:
//    1. Add Scores (ViewModel state + UI row)
//    In GameViewModel: Add scoreX and scoreO as mutableStateOf(0).
//    In makeMove: Increment the score of the winner when checkWinner() returns a player.
//    UI: Add a Row at the top or bottom of TicTacToeGame.kt displaying X: 5 | O: 3.
//
//    2. Haptics (Vibrate on click).
//    3. AI Mode (Random move for 'O').
    private val viewModel by viewModels<GameViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                TicTacTowGame(viewModel)
            }
        }
    }
}
