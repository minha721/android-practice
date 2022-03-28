package com.example.mvvm_practice

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("rt_score")
    val score: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("release_date")
    val release: String,
    @SerializedName("running_time")
    val time: String,
    @SerializedName("description")
    val description: String
)
