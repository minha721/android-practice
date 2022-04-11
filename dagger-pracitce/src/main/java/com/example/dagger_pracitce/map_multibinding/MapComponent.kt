package com.example.dagger_pracitce.map_multibinding

import dagger.Component

@Component(modules = [MapModule::class])
interface MapComponent {
    val getLongsByString: Map<String?, Long?>?
    val getStringsByClass: Map<Class<*>?, String?>?
}