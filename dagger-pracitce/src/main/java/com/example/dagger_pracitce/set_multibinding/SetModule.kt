package com.example.dagger_pracitce.set_multibinding

import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import kotlin.collections.HashSet

@Module
class SetModule {
    @Provides
    @IntoSet
    fun provideHello(): String {
        return "Hello"
    }

    @Provides
    @IntoSet
    fun provideWorld(): String {
        return "World"
    }

    @Provides
    @ElementsIntoSet
    fun provideSet(): Set<String?> {
        return HashSet(listOf("Charles", "Runa"))
    }
}