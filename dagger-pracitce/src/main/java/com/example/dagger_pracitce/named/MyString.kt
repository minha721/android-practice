package com.example.dagger_pracitce.named

import javax.inject.Inject
import javax.inject.Named

class MyString {
    @JvmField
    @Inject
    @Named("hello")
    var strHello: String? = null

    @JvmField
    @Inject
    @Named("world")
    var strWorld: String? = null
}