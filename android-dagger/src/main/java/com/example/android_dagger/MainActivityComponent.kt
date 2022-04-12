package com.example.android_dagger

import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun mainFragmentComponentBuilder(): MainFragmentComponent.Builder

    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun setModule(module: MainActivityModule): Builder
        @BindsInstance
        fun setActivity(activity: MainActivity): Builder
        fun build(): MainActivityComponent
    }
}