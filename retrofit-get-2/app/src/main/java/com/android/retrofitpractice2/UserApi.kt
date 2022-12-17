package com.android.retrofitpractice2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("{userId}")
    fun requestGetUser(@Path("userId") userId: String): Call<User>
}