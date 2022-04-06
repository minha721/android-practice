package com.example.dagger_pracitce.provider

import javax.inject.Inject
import javax.inject.Provider

class PCounter {
    @JvmField
    @Inject
    var provider: Provider<Int>? = null

    fun printProvider() {
        println("printing...")
        println(provider!!.get())
        println(provider!!.get())
    }
}