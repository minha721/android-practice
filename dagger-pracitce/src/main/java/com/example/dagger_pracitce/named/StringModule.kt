package com.example.dagger_pracitce.named

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class StringModule {
    @Provides
    @Named("hello")
    fun provideHello(): String {
        return "Hello"
    }

    @Provides
    @Named("world")
    fun provideWorld(): String {
        return "world"
    }
}