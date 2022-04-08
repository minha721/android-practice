package com.example.mvvm_practice.api

import com.example.mvvm_practice.helper.Constants
import com.example.mvvm_practice.models.GhibliResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(Constants.END_POINT_ALL)
    suspend fun getMovies(): Response<GhibliResponse>
}