package com.example.mvvm_practice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IGhibliService {
    @GET("films")
    fun requestMovieInfoAll(): Call<List<Movie>>

    @GET("films/{id}")
    fun requestMovieInfoId(@Path("id") id: String): Call<Detail>
}