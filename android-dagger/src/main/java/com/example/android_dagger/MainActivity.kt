package com.example.android_dagger

import javax.inject.Inject
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @JvmField
    @Inject
    var sharedPreferences: SharedPreferences? = null

    @JvmField
    @Inject
    var activityName: String? = null

    var component: MainActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component = (application as App).appComponent
            ?.mainActivityComponentBuilder()
            ?.setModule(MainActivityModule())
            ?.setActivity(this)
            ?.build()
        component!!.inject(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}