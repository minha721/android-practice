package com.android.retrofitpractice2

class RetrofitUtil {
    companion object{
        val gitUserService = MyApplication.retrofit.create(UserApi::class.java)
    }
}