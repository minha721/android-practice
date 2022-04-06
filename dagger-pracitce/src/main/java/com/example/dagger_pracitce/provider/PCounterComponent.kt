package com.example.dagger_pracitce.provider

import dagger.Component

@Component(modules = [PCounterModule::class])
interface PCounterComponent {
    fun inject(counter: PCounter)
}