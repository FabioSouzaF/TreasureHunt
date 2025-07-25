package com.example.treasurehunt.ui
//
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.example.treasurehunt.R
//
//@Composable
//fun RenderCanvas(
//    modifier: Modifier = Modifier
//
//){
//    var image = ImageBitmap.imageResource(R.drawable.pa)
//    var posX by remember() { mutableStateOf(10.dp) }
//    Column (
//        modifier = modifier
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "teste"
//        )
//
//        BoxWithConstraints (
//            modifier = Modifier
////                .fillMaxSize()
//                .height(200.dp)
//        ) {
////            val heigth = this.maxHeight
//            val tileSize = 60.dp
//            val numQuads  = this.maxWidth / tileSize
//
//            Canvas(modifier = Modifier.fillMaxSize()) {
//
//                repeat (numQuads.toInt()) { x ->
//                    val posX = tileSize * x
//
//                        drawRect(
//                            Color.Red,
//                            topLeft = Offset(posX.toPx() -10f, 20f),
//                            size = Size(tileSize.toPx() - 10, tileSize.toPx())
//                        )
//
//                }
//                translate (posX.toPx()) {
//                    drawImage(
//                        image,
//                        style = Size(10f,10f)
//                    )
//                }
//            }
//
//        }
//
//
//        Button(onClick = {
//            posX += 1.dp
//        }) {
//            Text("ok")
//        }
//
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun RenderCanvasPreview(){
//    RenderCanvas(modifier = Modifier.fillMaxSize())
//}

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntSize
import com.example.treasurehunt.R // Certifique-se de que este é o caminho correto para o seu R.drawable.pa

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RenderCanvas(
    modifier: Modifier = Modifier
) {
    // Carrega a imagem do jogador.
    // Certifique-se de que 'pa' é o nome do seu recurso de imagem em res/drawable.
    val playerImage = ImageBitmap.imageResource(id = R.drawable.pa)

    // Variáveis de estado do jogo
    val tileSize = 60.dp // Tamanho de cada célula do grid em Dp
    var playerX by remember { mutableStateOf(0) } // Coluna atual do jogador (0-indexed)
    var playerY by remember { mutableStateOf(0) } // Linha atual do jogador (0-indexed)
    var treasureX by remember { mutableStateOf(5) } // Coluna do tesouro (0-indexed, ajustada para 8x8)
    var treasureY by remember { mutableStateOf(7) } // Linha do tesouro (0-indexed, ajustada para 8x8)
    var gameMessage by remember { mutableStateOf("Encontre o tesouro!") } // Mensagem de status do jogo

    // Variáveis para armazenar o número de colunas e linhas do grid,
    // AGORA FIXAS em 8x8.
    val numCols = 8
    val numRows = 8

    Column(
        modifier = modifier
            .padding(16.dp) // Adiciona um preenchimento ao redor do conteúdo
    ) {
        // Exibe a mensagem de status do jogo
        Text(
            text = gameMessage,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // BoxWithConstraints permite obter as dimensões máximas disponíveis para o Canvas.
        // A altura é ajustada para acomodar o grid 8x8 (8 * 60.dp = 480.dp).
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth() // Ocupa a largura total disponível
                .height((tileSize * numRows)) // Altura calculada para 8 linhas
        ) {
            // Composable Canvas para desenhar os elementos do jogo
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Converte as dimensões Dp para pixels para uso no Canvas
                // Estas conversões foram movidas para dentro do escopo do Canvas.
                val canvasWidthPx = size.width // Usar size.width do DrawScope
                val canvasHeightPx = size.height // Usar size.height do DrawScope
                val tileSizePx = tileSize.toPx()

                // Desenha as células do grid
                for (row in 0 until numRows) {
                    for (col in 0 until numCols) {
                        val x = col * tileSizePx
                        val y = row * tileSizePx

                        // Desenha o fundo da célula
                        drawRect(
                            color = Color.LightGray, // Cor de fundo da célula
                            topLeft = Offset(x, y), // Posição superior esquerda da célula
                            size = Size(tileSizePx, tileSizePx) // Tamanho da célula
                        )

                        // Desenha a borda da célula
                        drawRect(
                            color = Color.DarkGray, // Cor da borda
                            topLeft = Offset(x, y),
                            size = Size(tileSizePx, tileSizePx),
                            style = Stroke(width = 2f) // Estilo de traçado para a borda
                        )
                    }
                }

                // Desenha o tesouro (um círculo amarelo)
                drawCircle(
                    color = Color.Yellow, // Cor do tesouro
                    radius = tileSizePx / 3f, // Raio do círculo (um terço do tamanho da célula)
                    center = Offset(
                        x = (treasureX * tileSizePx) + (tileSizePx / 2f), // Centraliza o tesouro na célula X
                        y = (treasureY * tileSizePx) + (tileSizePx / 2f)  // Centraliza o tesouro na célula Y
                    )
                )

                // Desenha o jogador
//                drawImage(
//                    image = playerImage, // Imagem do jogador
//                    topLeft = Offset(
//                        x = (playerX * tileSizePx), // Posição X do jogador na célula
//                        y = (playerY * tileSizePx)  // Posição Y do jogador na célula
//                    ),
//                    // Redimensiona a imagem para caber no tamanho da célula
//                    dstSize = IntSize(tileSizePx.toInt(), tileSizePx.toInt())
//                )

                drawImage(
                    playerImage,
                    topLeft = Offset(
                        x = (playerX * tileSizePx), // Posição X do jogador na célula
                        y = (playerY * tileSizePx)  // Posição Y do jogador na célula
                    ),
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre o grid e os botões

        // Botões de movimentação do jogador
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround // Distribui os botões igualmente
        ) {
            // Botão "Cima"
            Button(onClick = {
                if (playerY > 0) playerY-- // Move o jogador para cima se não estiver na borda superior
                // Verifica se o jogador encontrou o tesouro após o movimento
                if (playerX == treasureX && playerY == treasureY) {
                    gameMessage = "Você encontrou o tesouro!"
                } else {
                    gameMessage = "Encontre o tesouro!"
                }
            }) {
                Text("Cima")
            }
            // Botão "Baixo"
            Button(onClick = {
                if (playerY < numRows - 1) playerY++ // Move o jogador para baixo se não estiver na borda inferior
                if (playerX == treasureX && playerY == treasureY) {
                    gameMessage = "Você encontrou o tesouro!"
                } else {
                    gameMessage = "Encontre o tesouro!"
                }
            }) {
                Text("Baixo")
            }
            // Botão "Esquerda"
            Button(onClick = {
                if (playerX > 0) playerX-- // Move o jogador para a esquerda se não estiver na borda esquerda
                if (playerX == treasureX && playerY == treasureY) {
                    gameMessage = "Você encontrou o tesouro!"
                } else {
                    gameMessage = "Encontre o tesouro!"
                }
            }) {
                Text("Esquerda")
            }
            // Botão "Direita"
            Button(onClick = {
                if (playerX < numCols - 1) playerX++ // Move o jogador para a direita se não estiver na borda direita
                if (playerX == treasureX && playerY == treasureY) {
                    gameMessage = "Você encontrou o tesouro!"
                } else {
                    gameMessage = "Encontre o tesouro!"
                }
            }) {
                Text("Direita")
            }
        }
    }
}

/**
 * Preview para o componente RenderCanvas no Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun RenderCanvasPreview() {
    RenderCanvas(modifier = Modifier.fillMaxSize())
}