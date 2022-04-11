package com.example.dagger_pracitce.inheritance

import javax.inject.Inject

class Foobar {
    @JvmField
    @Inject
    var str: String? = null

    @JvmField
    @Inject
    var numInt: Int? = null
}