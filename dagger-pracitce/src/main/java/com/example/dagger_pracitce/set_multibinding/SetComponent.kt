package com.example.dagger_pracitce.set_multibinding

import dagger.Component

@Component(modules = [SetModule::class])
interface SetComponent {
    fun inject(foo: Foo)
}