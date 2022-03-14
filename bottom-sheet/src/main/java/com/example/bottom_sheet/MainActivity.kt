package com.example.bottom_sheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bottom_sheet.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity(), ListBottomSheetClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnName.setOnClickListener {
            TextBottomSheetFragment.newInstance().show(
                this.supportFragmentManager, TextBottomSheetFragment.TAG
            )
        }

        binding.btnSettings.setOnClickListener {
            ListBottomSheetFragment.newInstance().show(
                this.supportFragmentManager, ListBottomSheetFragment.TAG
            )
        }
    }

    override fun onButtonClicked(type: String) {
        Toast.makeText(this, "${type} is clicked", Toast.LENGTH_SHORT).show()
    }
}