package com.example.mvvm_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_practice.adapter.MovieAdapter
import com.example.mvvm_practice.databinding.ActivityMainBinding
import com.example.mvvm_practice.viewmodel.GhibliViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: GhibliViewModel by viewModels()
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        movieAdapter = MovieAdapter()

        binding.recycler.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

        viewModel.responseGhibli.observe(this) { listMovies ->
            movieAdapter.movies = listMovies
        }
    }
}