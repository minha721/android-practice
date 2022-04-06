package com.example.dagger_pracitce.named

import dagger.Component

@Component(modules = [StringModule::class])
interface StringComponent {
    fun inject(myString: MyString)
}