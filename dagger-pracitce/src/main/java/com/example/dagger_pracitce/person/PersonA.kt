package com.example.dagger_pracitce.person

import javax.inject.Inject

class PersonA @Inject constructor(val name: String, val age: Int)