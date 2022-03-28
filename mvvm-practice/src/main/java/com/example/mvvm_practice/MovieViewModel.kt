package com.example.mvvm_practice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {
    var okCode: MutableLiveData<Boolean> = MutableLiveData()
    var movieData: List<Movie> = listOf()

    fun requestMovieInfoAll() {
        GhibliAPI.requestMovieInfoAll().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                movieData = response?.body()!!
                okCode.value = true
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                okCode.value = false
                Log.d("minha - MovieVieModel", "${t.message}")
            }

        })
    }
}

class DetailViewModel: ViewModel() {
    var okCode: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var detailData: Detail

    fun requestMovieInfoId(id: String){
        GhibliAPI.requestMovieInfoId(id).enqueue(object : Callback<Detail> {
            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                detailData = response.body()?.let {
                    Detail(
                        id = it.id,
                        poster = it.poster,
                        title = it.title,
                        score = it.score,
                        director = it.director,
                        release = it.director,
                        time = it.time,
                        description = it.description
                    )
                } ?: Detail("", "", "", "", "", "", "", "")
                okCode.value = true
            }

            override fun onFailure(call: Call<Detail>, t: Throwable) {
                okCode.value = false
                Log.d("minha - DetailVieModel", "${t.message}")
            }

        })
    }
}