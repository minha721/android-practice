package com.example.mvvm_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvm_practice.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var detailViewModel: DetailViewModel
    var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        id = intent.getStringExtra("id").toString()

        callGhibliAPI()
        subscribeViewModel()
    }

    private fun callGhibliAPI() = detailViewModel.requestMovieInfoId(id)

    private fun subscribeViewModel() {
        detailViewModel.okCode.observe(this){
            if(it) {
                val detailData = detailViewModel.detailData
                binding.tvTitle.text = detailData.title

                Glide.with(this).load(detailData.poster).into(binding.ivPoster)

                val score = (detailData.score.toFloat()) / 20
                binding.rtRating.rating = score
                binding.tvDirector.text = detailData.director
                binding.tvReleaseDate.text = detailData.release
                binding.tvRunningTime.text = detailData.time
                binding.tvDescription.text = detailData.description
            }
        }
    }
}