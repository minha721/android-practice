package com.example.android_dagger

import android.content.Context
import javax.inject.Inject
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
    @JvmField
    @Inject
    var sharedPreferences: SharedPreferences? = null

    @JvmField
    @Inject
    var activityName: String? = null

    @JvmField
    @Inject
    var randomNumber: Int? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is MainActivity) {
            (activity as MainActivity?)!!.component
                ?.mainFragmentComponentBuilder()
                ?.setModule(MainFragmentModule())
                ?.setFragment(this)
                ?.build()
                ?.inject(this)
        }
        Log.d("MainFragment", activityName!!)
        Log.d("MainFragment", "randomNumber = $randomNumber")
    }
}