package com.example.dagger_pracitce.inheritance

import dagger.Component

@Component(modules = [ModuleA::class])
interface ComponentA {
    fun provideString(): String
}