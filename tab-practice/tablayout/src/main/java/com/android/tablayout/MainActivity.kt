package com.android.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.tablayout.databinding.ActivityMainBinding
import com.android.tablayout.fragment.BlankFragment1
import com.android.tablayout.fragment.BlankFragment2
import com.android.tablayout.fragment.BlankFragment3
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_main, BlankFragment1())
            .commit()

        binding.apply {
            tabLayoutMain.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab!!.position){
                        0 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_main, BlankFragment1())
                            .commit()

                        1 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_main, BlankFragment2())
                            .commit()

                        2 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_main, BlankFragment3())
                            .commit()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }
}