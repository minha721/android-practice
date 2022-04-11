package com.example.dagger_pracitce.subcomponent

import dagger.Module
import dagger.Provides

@Module
class MachineModule {

    @Provides
    fun provideCoffeeBean(): CoffeBean {
        return CoffeBean()
    }
}