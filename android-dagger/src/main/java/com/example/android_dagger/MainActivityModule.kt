package com.example.android_dagger

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module
abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun mainFragment(): MainFragment

    companion object {
        @Named("activity")
        @Provides
        @ActivityScope
        fun provideString(): String {
            return "String from MainActivityModule"
        }
    }
}