package com.example.android_dagger

import dagger.Binds
import dagger.multibindings.IntoMap
import dagger.multibindings.ClassKey
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import javax.inject.Named

@Module(subcomponents = [MainFragmentSubcomponent::class])
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainFragment::class)
    abstract fun bindInjectorFactory(factory: MainFragmentSubcomponent.Factory?): AndroidInjector.Factory<*>?

    companion object {
        @Named("activity")
        @Provides
        @ActivityScope
        fun provideString(): String {
            return "String from MainActivityModule"
        }
    }
}