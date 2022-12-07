package com.android.bottomnavi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.bottomnavi.databinding.ActivityMainBinding
import com.android.bottomnavi.fragment.BlankFragment1
import com.android.bottomnavi.fragment.BlankFragment2
import com.android.bottomnavi.fragment.BlankFragment3

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottomFragment1 -> {
                        changeFragment(BlankFragment1())
                    }
                    R.id.bottomFragment2 -> {
                        changeFragment(BlankFragment2())
                    }
                    R.id.bottomFragment3 -> {
                        changeFragment(BlankFragment3())
                    }
                }
                true
            }

            bottomNav.selectedItemId = R.id.bottomFragment1
        }
    }

    fun changeFragment(fragment: Fragment) {
        binding.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit()
        }
    }
}