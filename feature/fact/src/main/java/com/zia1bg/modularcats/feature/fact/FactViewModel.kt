package com.zia1bg.modularcats.feature.fact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zia1bg.modularcats.core.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {

    private val _fact: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getObservableFact() = _fact

    fun getCatFact() {
        viewModelScope.launch {
            val fact = repository.getFact()
            _fact.postValue(fact.text)
        }
    }


}