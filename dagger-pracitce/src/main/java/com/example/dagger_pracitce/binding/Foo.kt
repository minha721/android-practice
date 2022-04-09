package com.example.dagger_pracitce.binding

import dagger.Lazy
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

class Foo {
    @JvmField
    @Inject
    var str: Optional<String>? = null

    @JvmField
    @Inject
    var str2: Optional<Provider<String>>? = null

    @JvmField
    @Inject
    var str3: Optional<Lazy<String>>? = null
}