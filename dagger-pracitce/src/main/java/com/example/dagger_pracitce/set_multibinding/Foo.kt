package com.example.dagger_pracitce.set_multibinding

import javax.inject.Inject

class Foo {
    @JvmField
    @Inject
    var strings: Set<String>? = null

    fun print() {
        val itr: Iterator<*> = strings!!.iterator()
        while (itr.hasNext()) {
            println(itr.next())
        }
    }
}