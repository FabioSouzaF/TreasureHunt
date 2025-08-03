package com.example.treasurehunt

import GameGrid
import GameState
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.treasurehunt.ui.theme.TreasureHuntTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {
    private val viewModel: TreasureHunterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreasureHuntTheme {
               Surface (
                   modifier = Modifier.fillMaxSize(),
                   color = MaterialTheme.colorScheme.background
               ) {
                   GameScreen(viewModel)
               }
            }
        }
    }
}

@SuppressLint("MutableCollectionMutableState", "StateFlowValueCalledInComposition")
@Composable
fun GameScreen(viewModel: TreasureHunterViewModel) {
    val gameState = remember { GameState(viewModel) }
//    var gameState by remember { mutableStateOf(GameState()) }

    var grid by remember { mutableStateOf(gameState.grid) }
    val qtdObstacules by viewModel.obstacules.collectAsStateWithLifecycle()
    Log.i("gameState","$gameState")
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Quantidade de obstaculoes: ${qtdObstacules}")
        Box(modifier = Modifier.align(Alignment.Center)
        ) {
            GameGrid(
                gameState = gameState,
               grid = grid,
                modifier = Modifier.align(Alignment.Center)
            )
        }


        DPadMovementControls(
            onMoveLeft = {
                gameState.movePlayer(gameState.playerPosition.first - 1, gameState.playerPosition.second)
            },
            onMoveRight = {
                gameState.movePlayer(gameState.playerPosition.first + 1, gameState.playerPosition.second)
            },
            onMoveUp = {
                gameState.movePlayer(gameState.playerPosition.first, gameState.playerPosition.second - 1)
            },
            onMoveDown = {
                gameState.movePlayer(gameState.playerPosition.first, gameState.playerPosition.second + 1)
            }
        )


        ActionButtons(
            onDigClick = { gameState.getItem(gameState.playerPosition.first, gameState.playerPosition.second) },
            onMetalDetectorClick = { /* Lógica para usar detector */ },
            onDynamiteClick = { /* Lógica para usar dinamite */ }
        )
    }
}

@Preview(showBackground = true, widthDp = 720, heightDp = 400) // Preview em modo horizontal
@Composable
fun PreviewGameScreen() {
//    GameScreen()
}
