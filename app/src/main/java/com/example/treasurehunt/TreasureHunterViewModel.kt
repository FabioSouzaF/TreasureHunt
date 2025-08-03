package com.example.treasurehunt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TreasureHunterViewModel : ViewModel() {
    private val _obstacules = MutableStateFlow(0)
    val obstacules: StateFlow<Int> = _obstacules.asStateFlow()

    fun addObstacules(){
        Log.i("","Ta entrando aqui")
        _obstacules.value++
    }



}