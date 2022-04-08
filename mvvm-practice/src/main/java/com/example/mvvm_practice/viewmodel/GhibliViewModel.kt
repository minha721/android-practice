package com.example.mvvm_practice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_practice.models.GhibliItem
import com.example.mvvm_practice.repository.GhibliRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GhibliViewModel
@Inject
constructor(private val repository: GhibliRepository): ViewModel() {

    private val _response = MutableLiveData<List<GhibliItem>>()
    val responseGhibli: LiveData<List<GhibliItem>>
        get() = _response

    init {
        getAllMovies()
    }

    private fun getAllMovies() = viewModelScope.launch {
        repository.getMovies().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("minha tag", "getAllMovies Error : ${response.code()}")
            }
        }
    }
}