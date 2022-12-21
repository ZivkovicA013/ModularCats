package com.zia1bg.modularcats.feature.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zia1bg.modularcats.core.data.model.Fact
import com.zia1bg.modularcats.core.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {


    // Backing property to avoid state updates from other classes
    private val _catFactState = MutableStateFlow(FactUiState(false))

    // The UI collects from this StateFlow to get its state updates
    val catFactState: StateFlow<FactUiState> = _catFactState


    fun getCatFact() {
        viewModelScope.launch {
            _catFactState.value = FactUiState(isLoading = true)
            val fact = repository.getFact()
            _catFactState.value = FactUiState(isLoading = false, fact = fact)
        }
    }

}

data class FactUiState(
    val isLoading: Boolean = false,
    val fact: Fact? = null,
)
