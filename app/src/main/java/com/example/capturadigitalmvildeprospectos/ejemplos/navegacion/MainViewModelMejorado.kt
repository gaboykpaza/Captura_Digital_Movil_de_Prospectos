package com.example.capturadigitalmvildeprospectos.ejemplos.navegacion

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModelMejorado @Inject constructor() : ViewModel() {
    private val _conteo = MutableStateFlow(0)
    val conteo: StateFlow<Int> = _conteo

    fun incrementar() { _conteo.value += 1 }
}