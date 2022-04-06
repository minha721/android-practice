package com.example.dagger_pracitce.lazy

import dagger.Component

@Component(modules = [CounterModule::class])
interface CounterComponent {
    fun inject(counter: Counter)
}