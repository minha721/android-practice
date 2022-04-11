package com.example.dagger_pracitce.inheritance

import dagger.Module
import dagger.Provides

@Module
class ModuleB {
    @Provides
    fun provideInt(): Int {
        return 100
    }
}