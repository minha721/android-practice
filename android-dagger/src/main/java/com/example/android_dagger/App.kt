package com.example.android_dagger

import android.app.Application
import com.example.android_dagger.AppComponent

class App : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this, AppModule())
    }
}