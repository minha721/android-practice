package com.example.dagger_pracitce.binding

import dagger.Component

@Component(modules = [CommonModule::class])
interface NoStrComponent {
    fun inject(foo: Foo)
}