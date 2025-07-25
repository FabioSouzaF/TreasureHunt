package com.example.treasurehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.treasurehunt.ui.theme.TreasureHuntTheme


@Composable
fun DPadMovementControls(
    onMoveLeft: () -> Unit,
    onMoveRight: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit
) {
    TreasureHuntTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column (
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(16.dp)
            ) {

                OutlinedButton (
                    onClick = onMoveUp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(60.dp)
                ) {
                    Text("▲")
                }

                Row {

                    OutlinedButton(
                        onClick = onMoveLeft,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Text("◀")
                    }


                    OutlinedButton(
                        onClick = {  },
                        modifier = Modifier
                            .size(60.dp)
                            .offset(y = 1.dp)
                    ) {
//                        botao para ficar no centro e espacar
                    }

                    // Botão "Right"
                    OutlinedButton(
                        onClick = onMoveRight,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Text("▶")
                    }
                }

                // Botão "Down"
                OutlinedButton(
                    onClick = onMoveDown,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(60.dp)
                ) {
                    Text("▼")
                }
            }
        }
    }
}