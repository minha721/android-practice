package com.example.dagger_pracitce.binding

import dagger.BindsOptionalOf
import dagger.Module

@Module
abstract class CommonModule {
    @BindsOptionalOf
    abstract fun bindsOptionalOfString(): String
}