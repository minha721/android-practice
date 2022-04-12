package com.example.android_dagger

import android.content.Context
import javax.inject.Inject
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Named

class MainFragment : Fragment() {
    @JvmField
    @Inject
    @Named("app")
    var appString: String? = null

    @JvmField
    @Inject
    @Named("activity")
    var activityString: String? = null

    @JvmField
    @Inject
    @Named("fragment")
    var fragmentString: String? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        Log.e("MainFragment", appString!!)
        Log.e("MainFragment", activityString!!)
        Log.e("MainFragment", fragmentString!!)

        super.onAttach(context)
    }
}