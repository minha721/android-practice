package com.example.dagger_pracitce.inheritance

import dagger.Component

@Component(modules = [ModuleB::class], dependencies = [ComponentA::class])
interface ComponentB {
    fun inject(foobar: Foobar)
}