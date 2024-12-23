package com.example.ilyaniafiodau

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class PlacesViewModel : ViewModel() {
    private val _PlacesState = MutableStateFlow<UiState<List<Place>>>(UiState.Loading)
    val PlacesState: StateFlow<UiState<List<Place>>> = _PlacesState

    fun fetchPlaces() {
        viewModelScope.launch {
            try {
                val Places = MockApi.getPlaces()
                if (Places.isEmpty()) {
                    _PlacesState.value = UiState.Empty
                } else {
                    _PlacesState.value = UiState.Success(Places)
                }
            } catch (e: Exception) {
                _PlacesState.value = UiState.Error("Loading failure")
            }
        }
    }
}
