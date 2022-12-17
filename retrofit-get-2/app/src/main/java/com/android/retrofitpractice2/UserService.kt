package com.android.retrofitpractice2

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService {
    fun getUserById(userId: String, callback: RetrofitCallback<User>) {
        RetrofitUtil.gitUserService.requestGetUser(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("레트로핏 에러", "onFailure Throwable -> ${t.message}")
                callback.onError(t)
            }
        })
    }
}