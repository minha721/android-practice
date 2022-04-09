package com.example.dagger_pracitce.binding

import dagger.Module
import dagger.Provides

@Module
class HelloModule {
    @Provides
    fun provideString(): String {
        return "Hello"
    }
}