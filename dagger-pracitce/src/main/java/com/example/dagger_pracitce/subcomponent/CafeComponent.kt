package com.example.dagger_pracitce.subcomponent

import dagger.Component

@Component(modules = [CafeModule::class])
interface CafeComponent {
    fun inject(cafe: Cafe): Unit
}