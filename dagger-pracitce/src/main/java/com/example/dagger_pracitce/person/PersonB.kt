package com.example.dagger_pracitce.person

import javax.inject.Inject

class PersonB {
    @JvmField
    @Inject
    var name: String? = null

    var age = 0

    @JvmName("setAge1")
    @Inject
    fun setAge(age: Int) {
        this.age = age
    }
}