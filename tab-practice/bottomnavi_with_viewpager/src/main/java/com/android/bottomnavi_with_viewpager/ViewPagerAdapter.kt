package com.android.bottomnavi_with_viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.bottomnavi_with_viewpager.fragment.BlankFragment1
import com.android.bottomnavi_with_viewpager.fragment.BlankFragment2
import com.android.bottomnavi_with_viewpager.fragment.BlankFragment3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> BlankFragment1()
            1 -> BlankFragment2()
            2 -> BlankFragment3()
            else -> BlankFragment1()
        }
    }
}