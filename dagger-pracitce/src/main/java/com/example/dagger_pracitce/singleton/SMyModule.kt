package com.example.dagger_pracitce.singleton

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SMyModule {
    @Provides
    @Singleton
    fun provideObject(): Object {
        return Object()
    }
}