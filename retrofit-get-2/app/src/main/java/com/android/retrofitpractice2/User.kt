package com.android.retrofitpractice2

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    val image: String,
    val name: String,
    @SerializedName("login")
    val userId: String,
    @SerializedName("public_repos")
    val repos: Int
)