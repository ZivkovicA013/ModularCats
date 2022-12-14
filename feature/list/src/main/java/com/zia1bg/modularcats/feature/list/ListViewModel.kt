package com.zia1bg.modularcats.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zia1bg.modularcats.core.data.model.Breed
import com.zia1bg.modularcats.core.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {

    private var _breeds = MutableLiveData<List<Breed>>()
    val breed: LiveData<List<Breed>> = _breeds

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        viewModelScope.launch {
            _loading.value = true
            _breeds.value = repository.getBreeds()
            _loading.value = false
        }
    }


}