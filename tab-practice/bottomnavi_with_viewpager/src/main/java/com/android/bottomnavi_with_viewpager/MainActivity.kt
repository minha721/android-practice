package com.android.bottomnavi_with_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.android.bottomnavi_with_viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

            // 뷰페이저 변경시 바텀 탭도 변경되도록
            viewPager.registerOnPageChangeCallback(PageChangeCallback())

            // 바텀 탭 클릭시 뷰페이저 이동
            bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottomFragment1 -> {
                        viewPager.currentItem = 0
                    }
                    R.id.bottomFragment2 -> {
                        viewPager.currentItem = 1
                    }
                    R.id.bottomFragment3 -> {
                        viewPager.currentItem = 2
                    }
                }
                true
            }
        }
    }

    inner class PageChangeCallback: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bottomNav.selectedItemId = when (position) {
                0 -> R.id.bottomFragment1
                1 -> R.id.bottomFragment2
                2 -> R.id.bottomFragment3
                else -> error("no such position: $position")
            }
        }
    }
}