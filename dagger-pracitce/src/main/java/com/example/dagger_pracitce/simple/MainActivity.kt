package com.example.dagger_pracitce.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dagger_pracitce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myComponent = DaggerMyComponent.create()
        binding.tvText.text = myComponent.getString()
    }
}