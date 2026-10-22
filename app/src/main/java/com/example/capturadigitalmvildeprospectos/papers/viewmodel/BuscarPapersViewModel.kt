package com.example.capturadigitalmvildeprospectos.papers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capturadigitalmvildeprospectos.papers.model.Paper
import com.example.capturadigitalmvildeprospectos.papers.repository.PaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class BuscarUiState {
    object Idle : BuscarUiState()
    object Loading : BuscarUiState()
    data class Success(val papers: List<Paper>) : BuscarUiState()
    data class Error(val message: String) : BuscarUiState()
}

@HiltViewModel
class BuscarPapersViewModel @Inject constructor(
    private val repository: PaperRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BuscarUiState>(BuscarUiState.Idle)
    val uiState: StateFlow<BuscarUiState> = _uiState

    fun buscar(query: String) {
        if (query.isBlank()) return
        viewModelScope.launch {
            _uiState.value = BuscarUiState.Loading
            try {
                val resultados = repository.buscarPapers(query)
                _uiState.value = BuscarUiState.Success(resultados)
            } catch (e: Exception) {
                _uiState.value = BuscarUiState.Error(e.localizedMessage ?: "Error de red")
            }
        }
    }

    fun toggleFavorito(paper: Paper) {
        viewModelScope.launch {
            repository.toggleFavorito(paper)
            if (_uiState.value is BuscarUiState.Success) {
                val list = (_uiState.value as BuscarUiState.Success).papers.map {
                    if (it.id == paper.id) it.copy(esFavorito = !it.esFavorito) else it
                }
                _uiState.value = BuscarUiState.Success(list)
            }
        }
    }
}