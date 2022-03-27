package com.example.youtube_player

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.youtube_player.databinding.ActivityMainBinding
import com.google.android.material.slider.RangeSlider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var myYoutubePlayer: YouTubePlayer
    val timeFormat = DecimalFormat("00")
    val videoId = "EtE09lowIbk"
    val videoLength = 22198.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(binding.youtubePlayer)

        // 처음 로딩할 때 세팅
        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                myYoutubePlayer = youTubePlayer
                myYoutubePlayer.loadVideo(videoId, 0f)

                changeStart(0.0f)
                changeEnd(videoLength)
            }
        })

        binding.rangeSlider.valueFrom = 0.0f
        binding.rangeSlider.valueTo = videoLength
        binding.rangeSlider.values = arrayListOf(0.0f, videoLength)

        var bStartTime = 0.0f
        var bEndTime = 22198.0f
        var aStartTime = 0.0f
        var aEndTime = 22198.0f

        // 슬라이더 움직일 때
        binding.rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener{
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: RangeSlider) {
                aStartTime = binding.rangeSlider.values[0].toString().toFloat()
                aEndTime = binding.rangeSlider.values[1].toString().toFloat()

                // startTime만 변한 경우
                if(bStartTime!=aStartTime && bEndTime==aEndTime) {
                    changeStart(aStartTime)
                    myYoutubePlayer.seekTo(aStartTime)
                    bStartTime = aStartTime
                }

                // endTime만 변한 경우
                if(bStartTime==aStartTime && bEndTime!=aEndTime) {
                    changeEnd(aEndTime)
                    myYoutubePlayer.seekTo(aEndTime)
                    bEndTime = aEndTime
                }
            }
        })

        binding.btnSave.setOnClickListener {
            val startTime = binding.tvStartTime.text.toString()
            val endTime = binding.tvEndTime.text.toString()
            Toast.makeText(this, "startTime : $startTime\nendTime: $endTime", Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeStart(time: Float) {
        binding.tvStartTime.text = getTime(getHour(time), getMin(time), getSec(time))
        binding.tvSlideStart.text = getTime(getHour(time), getMin(time), getSec(time))
    }

    private fun changeEnd(time: Float) {
        binding.tvEndTime.text = getTime(getHour(time), getMin(time), getSec(time))
        binding.tvSlideEnd.text = getTime(getHour(time), getMin(time), getSec(time))
    }

    fun getTime(hour: String, min:String, sec: String): String{
        return "${hour}:${min}:${sec}"
    }

    fun getHour(time: Float): String {
        val hour = timeFormat.format((time/3600).toInt())
        return hour
    }

    fun getMin(time: Float): String {
        val min = timeFormat.format((time%3600/60).toInt())
        return min
    }

    fun getSec(time: Float): String {
        val sec = timeFormat.format((time%3600%60).toInt())
        return sec
    }
}