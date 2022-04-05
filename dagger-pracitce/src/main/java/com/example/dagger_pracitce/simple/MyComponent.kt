package com.example.dagger_pracitce.simple

import dagger.Component

@Component(modules = [MyModule::class])
interface MyComponent {
    fun getString(): String
}