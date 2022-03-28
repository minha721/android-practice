package com.example.mvvm_practice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GhibliService {
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var service: IGhibliService = retrofit.create(IGhibliService::class.java)
}