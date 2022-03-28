package com.example.mvvm_practice

import retrofit2.Call

object GhibliAPI {
    fun requestMovieInfoAll(): Call<List<Movie>> {
        return GhibliService.service.requestMovieInfoAll()
    }

    fun requestMovieInfoId(id: String): Call<Detail> {
        return GhibliService.service.requestMovieInfoId(id)
    }
}