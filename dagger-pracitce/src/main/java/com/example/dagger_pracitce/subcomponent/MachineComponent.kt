package com.example.dagger_pracitce.subcomponent

import dagger.Subcomponent

@Subcomponent(modules = [MachineModule::class])
interface MachineComponent {

    fun getCoffee(): Coffee

    fun inject(machine: Machine)

    @Subcomponent.Builder
    interface Builder {
        fun setMachineModule(coffeeMachineModule: MachineModule): Builder
        fun build(): MachineComponent
    }
}