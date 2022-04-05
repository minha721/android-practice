package com.example.dagger_pracitce.simple

import dagger.Module
import dagger.Provides

@Module
class MyModule {
    @Provides
    fun provideHelloWorld(): String {
        return "Hello World"
    }
}