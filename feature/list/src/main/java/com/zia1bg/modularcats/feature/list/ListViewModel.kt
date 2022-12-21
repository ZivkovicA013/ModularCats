package com.zia1bg.modularcats.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zia1bg.modularcats.core.data.model.Breed
import com.zia1bg.modularcats.core.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {

    private val _breedsUiState = MutableStateFlow(BreedsUiState(isLoading = true))

    val breedUiState: StateFlow<BreedsUiState> = _breedsUiState

    init {
        viewModelScope.launch {
            _breedsUiState.value = BreedsUiState(isLoading = false, repository.getBreeds())
        }
    }


}

data class BreedsUiState(
    val isLoading: Boolean = false,
    val breeds: List<Breed>? = null
)

