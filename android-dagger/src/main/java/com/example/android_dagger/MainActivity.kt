package com.example.android_dagger

import android.os.Bundle
import android.util.Log
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class MainActivity : DaggerAppCompatActivity() {
    @JvmField
    @Inject
    @Named("app")
    var appString: String? = null

    @JvmField
    @Inject
    @Named("activity")
    var activityString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        Log.e("MainActivity", appString!!)
        Log.e("MainActivity", activityString!!)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}