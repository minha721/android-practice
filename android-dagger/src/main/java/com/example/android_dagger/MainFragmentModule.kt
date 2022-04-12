package com.example.android_dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MainFragmentModule {
    @Named("fragment")
    @Provides
    @FragmentScope
    fun provideString(): String {
        return "String from fragment"
    }
}