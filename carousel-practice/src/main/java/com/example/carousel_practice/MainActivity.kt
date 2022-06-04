package com.example.carousel_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carousel_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var carouselAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<CarouselData>()
        list.add(CarouselData(R.drawable.alcohol_1, "고흥유자주"))
        list.add(CarouselData(R.drawable.alcohol_2, "구기홍주"))
        list.add(CarouselData(R.drawable.alcohol_3, "면천두견주"))
        list.add(CarouselData(R.drawable.alcohol_4, "능이주"))
        list.add(CarouselData(R.drawable.alcohol_5, "DOK 막걸리"))
        list.add(CarouselData(R.drawable.alcohol_6, "고도리 로제와인"))

        carouselAdapter = CarouselAdapter(list)

        binding.rvCarousel.apply {
            adapter = carouselAdapter
            setAlpha(true)
            setIntervalRatio(0.5f)
        }
    }
}