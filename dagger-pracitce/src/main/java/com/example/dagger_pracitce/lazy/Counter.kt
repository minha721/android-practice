package com.example.dagger_pracitce.lazy

import dagger.Lazy
import javax.inject.Inject

class Counter {
    @JvmField
    @Inject
    var lazy: Lazy<Int>? = null

    fun printLazy() {
        println("printing...")
        println(lazy)
        println(lazy!!.get())
        println(lazy!!.get())
    }
}