package com.example.mvvm_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        callGhibliAPI()
        subscribeViewModel()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    private fun callGhibliAPI() = movieViewModel.requestMovieInfoAll()

    private fun subscribeViewModel() {
        movieViewModel.okCode.observe(this) {
            if(it) {
                val datas = movieViewModel.movieData.sortedBy { it.score }
                binding.recycler.adapter = MovieAdapter(datas)
            } else {
                Toast.makeText(this, "영화 조회를 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}