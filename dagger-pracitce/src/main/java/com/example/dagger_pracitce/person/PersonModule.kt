package com.example.dagger_pracitce.person

import dagger.Module
import dagger.Provides

@Module
class PersonModule {
    @Provides
    fun provideName(): String {
        return "Minha"
    }

    @Provides
    fun provideAge(): Int {
        return 25
    }
}