package com.android.bottomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.android.bottomtest.databinding.ActivityMainBinding

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
            registerOnPageChangeCallback(PageChangeCallback())
            isUserInputEnabled = false
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            navigationSelected(it)
        }

        binding.fab.setOnClickListener {
            binding.viewpager.currentItem = 1
        }
    }

    private fun navigationSelected(item: MenuItem): Boolean {
        val checked = item.setChecked(true)

        when (checked.itemId) {
            R.id.mapFragment -> {
                binding.viewpager.currentItem = 0
                return true
            }
            R.id.profileFragment -> {
                binding.viewpager.currentItem = 2
                return true
            }
        }
        return false
    }

    private inner class PageChangeCallback: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bottomNavigationView.selectedItemId = when (position) {
                0 -> R.id.mapFragment
                1 -> R.id.homeFragment
                2 -> R.id.profileFragment
                else -> error("no such position: $position")
            }
        }
    }
}