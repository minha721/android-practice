package com.example.dagger_pracitce.subcomponent

import javax.inject.Inject

class Cafe {
    @Inject
    lateinit var coffeeMachine: Machine

    init {
        DaggerCafeComponent.create().inject(this)
    }

    fun orderCoffee(): Coffee {
        return coffeeMachine.extract()
    }
}