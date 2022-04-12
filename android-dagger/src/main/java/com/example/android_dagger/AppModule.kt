package com.example.android_dagger

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named
import javax.inject.Singleton

@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class AppModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    companion object {
        @Named("app")
        @Provides
        @Singleton
        fun provideString(): String {
            return "String from AppModule"
        }
    }
}