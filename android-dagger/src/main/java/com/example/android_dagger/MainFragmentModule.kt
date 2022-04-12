package com.example.android_dagger

import dagger.Module
import dagger.Provides
import java.util.*

@Module
class MainFragmentModule {
    @Provides
    @FragmentScope
    fun provideInt(): Int {
        return Random().nextInt()
    }
}