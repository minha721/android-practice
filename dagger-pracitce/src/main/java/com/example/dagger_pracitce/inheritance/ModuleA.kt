package com.example.dagger_pracitce.inheritance

import dagger.Module
import dagger.Provides

@Module
class ModuleA {
    @Provides
    fun provideString(): String {
        return "String from ModuleA"
    }
}