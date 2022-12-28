package com.ssafy.mycustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.Dimension
import com.ssafy.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val type = 6
    var idx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnChange.setOnClickListener {
                changeWeather()
            }
        }
    }

    private fun changeWeather() {
        idx = (idx + 1) % type

        binding.apply {
            when(idx) {
                0 -> {
                    customWeather.img.setImageResource(R.drawable.sunny)
                    customWeather.txt.apply {
                        text = "Sunny"
                        setTextSize(Dimension.SP, 16F)
                        setTextColor(context.resources.getColor(R.color.sunny))
                    }
                }
                1 -> {
                    customWeather.img.setImageResource(R.drawable.cloud)
                    customWeather.txt.apply {
                        text = "Cloud"
                        setTextSize(Dimension.SP, 20F)
                        setTextColor(context.resources.getColor(R.color.cloud))
                    }
                }
                2 -> {
                    customWeather.img.setImageResource(R.drawable.rain)
                    customWeather.txt.apply {
                        text = "Rain"
                        setTextSize(Dimension.SP, 24F)
                        setTextColor(context.resources.getColor(R.color.rain))
                    }
                }
                3 -> {
                    customWeather.img.setImageResource(R.drawable.snow)
                    customWeather.txt.apply {
                        text = "Snow"
                        setTextSize(Dimension.SP, 28F)
                        setTextColor(context.resources.getColor(R.color.snow))
                    }
                }
                4 -> {
                    customWeather.img.setImageResource(R.drawable.thunder)
                    customWeather.txt.apply {
                        text = "Thunder"
                        setTextSize(Dimension.SP, 32F)
                        setTextColor(context.resources.getColor(R.color.thunder))
                    }
                }
                5 -> {
                    customWeather.img.setImageResource(R.drawable.hail)
                    customWeather.txt.apply {
                        text = "Hail"
                        setTextSize(Dimension.SP, 36F)
                        setTextColor(context.resources.getColor(R.color.hail))
                    }
                }
            }
        }
    }
}