package com.example.treasurehunt

import GameState
import android.R
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TreasureHunterViewModel : ViewModel() {
    private val _obstacules = MutableStateFlow(0)
    val obstacules: StateFlow<Int> = _obstacules.asStateFlow()

    private val _useMetalDetector = MutableStateFlow(false)
    val useMetalDetector: StateFlow<Boolean> = _useMetalDetector.asStateFlow()

    private val _numDigUse = MutableStateFlow(64)
    private val _numMetalDetectorUse = MutableStateFlow(3)
    private val _numDynamiteUse = MutableStateFlow(3)

    val numDigUse: StateFlow<Int> = _numDigUse.asStateFlow()
    val numMetalDetectorUse: StateFlow<Int> = _numMetalDetectorUse.asStateFlow()
    val numDynamiteUse: StateFlow<Int> = _numDynamiteUse.asStateFlow()

    fun addObstacules(){
        Log.i("","Ta entrando aqui")
        _obstacules.value++
    }

    fun useMetalDetector(){
        if (_numMetalDetectorUse.value == 0) return
        _useMetalDetector.value = true
        _numMetalDetectorUse.value--
        viewModelScope.launch {
            // Espera 1000 milissegundos (1 segundo)
            delay(1000)
            _useMetalDetector.value = false
        }
    }

    fun useDig(gameState: GameState){
        gameState.getItem(gameState.playerPosition.first, gameState.playerPosition.second)
        _numDigUse.value--
    }

    fun useDynamite(gameState: GameState){
        _numDynamiteUse.value--
        //LOGICA DA DINAMITE
    }




}