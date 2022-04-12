package com.example.android_dagger

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.ClassKey
import dagger.android.AndroidInjector
import javax.inject.Named
import javax.inject.Singleton

@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class AppModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindAndroidInjectorFactory(factory: MainActivitySubcomponent.Factory?): AndroidInjector.Factory<*>?

    companion object {
        @Named("app")
        @Provides
        @Singleton
        fun provideString(): String {
            return "String from AppModule"
        }
    }
}