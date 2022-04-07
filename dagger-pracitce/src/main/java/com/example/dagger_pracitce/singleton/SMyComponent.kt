package com.example.dagger_pracitce.singleton

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SMyModule::class])
interface SMyComponent {
    fun getObject(): Object
}