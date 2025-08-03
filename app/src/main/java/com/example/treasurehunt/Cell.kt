package com.example.treasurehunt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Cell (

    val x: Int = 0,
    val y: Int = 0,
    initialContent: CellContent = CellContent.EMPTY,
    initialIsRevealed: Boolean = false)
{
    var content by mutableStateOf(initialContent)
    var isRevealed by mutableStateOf(initialIsRevealed)
}


