package com.example.android_dagger

import android.app.Application
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import dagger.android.AndroidInjector

class App : Application(), HasAndroidInjector {
    @JvmField
    @Inject
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory()
            .create(this)
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector!!
    }
}