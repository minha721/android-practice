package com.example.android_dagger

import javax.inject.Inject
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Named

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @JvmField
    @Inject
    var androidInjector: DispatchingAndroidInjector<Any>? = null

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

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector!!
    }
}