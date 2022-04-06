package com.example.dagger_pracitce.provider

import dagger.Module
import dagger.Provides

@Module
class PCounterModule {
    var next: Int = 100

    @Provides
    fun provideInteger(): Int {
        println("computing...")
        return next++
    }
}