package com.example.dagger_pracitce.person

import dagger.Component

@Component(modules = [PersonModule::class])
interface PersonComponent {

    fun getPersonA(): PersonA

    fun inject(personB: PersonB): Unit

}