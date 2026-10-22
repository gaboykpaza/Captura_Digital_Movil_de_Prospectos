package com.example.capturadigitalmvildeprospectos.papers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capturadigitalmvildeprospectos.papers.model.Paper
import com.example.capturadigitalmvildeprospectos.papers.repository.PaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritosViewModel @Inject constructor(
    private val repository: PaperRepository
) : ViewModel() {

    val favoritos: StateFlow<List<Paper>> = repository.getFavoritos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun eliminarFavorito(paper: Paper) {
        viewModelScope.launch {
            repository.toggleFavorito(paper)
        }
    }
}