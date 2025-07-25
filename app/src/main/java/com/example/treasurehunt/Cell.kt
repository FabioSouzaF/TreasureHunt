package com.example.treasurehunt

class Cell (

    val x: Int = 0,
    val y: Int = 0,
    var content: CellContent = CellContent.EMPTY,
    var isRevealed: Boolean = false

)