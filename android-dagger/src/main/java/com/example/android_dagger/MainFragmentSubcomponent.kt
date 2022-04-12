package com.example.android_dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [MainFragmentModule::class])
interface MainFragmentSubcomponent: AndroidInjector<MainFragment> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainFragment>{

    }
}