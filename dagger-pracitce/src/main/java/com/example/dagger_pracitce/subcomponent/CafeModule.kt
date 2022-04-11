package com.example.dagger_pracitce.subcomponent

import dagger.Module
import dagger.Provides

@Module(subcomponents = [MachineComponent::class])
class CafeModule {

    @Provides
    fun provideWater(): Water {
        return Water()
    }

    @Provides
    fun provideMachine(builder: MachineComponent.Builder): Machine {
        return Machine(builder)
    }
}