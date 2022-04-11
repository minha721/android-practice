package com.example.dagger_pracitce.map_multibinding

import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
class MapModule {

    @Provides
    @IntoMap
    @StringKey("foo")
    fun provideFooValue(): Long {
        return 100L
    }

    @Provides
    @IntoMap
    @ClassKey(Foo::class)
    fun provideFooStr(): String {
        return "Foo String"
    }
}