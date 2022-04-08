package com.example.mvvm_practice.repository

import com.example.mvvm_practice.api.ApiService
import javax.inject.Inject

class GhibliRepository
@Inject
constructor(private val apiService: ApiService) {
    suspend fun getMovies() = apiService.getMovies()
}