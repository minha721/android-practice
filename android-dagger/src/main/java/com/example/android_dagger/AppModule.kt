package com.example.android_dagger

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MainActivityComponent::class])
class AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(app: App): SharedPreferences {
        return app.getSharedPreferences(
            "default",
            Context.MODE_PRIVATE
        )
    }
}