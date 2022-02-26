package com.example.kotlin_practice

fun main() {
    print("1. fun nullCheck() : ")
    nullCheck()
    print("2. fun elvis() : ")
    elvis()
    print("3. fun nonNull() : ")
    nonNull("minha")
    print("4. fun nullSafe() : ")
    nullSafe()
    println("5. class")
    val human = Human("minha")
    human.running()
    println("my name is ${human.name}")
    println("6. inheritance")
    val korean = Korean()
    korean.running()
    println("my name is ${human.name}")
}

fun nullCheck() {
    var nullName: String? = null
    val upperCaseName: String? = nullName?.toUpperCase()
    println(upperCaseName)
}

fun elvis() {
    var name = "Minha"
    var lastName: String? = null
    val fullName = name + " " + (lastName?: "No lastName")
    println(fullName)
}

fun nonNull(str: String?) {
    var notNullName: String = str!!
    val upperCaseName = notNullName.toUpperCase()
    println(upperCaseName)
}

fun nullSafe() {
    val name: String? = "Minha Lee"
    name?.let{
        println("My name is ${name}")
    }
}

open class Human (val name: String = "Anonymous") {
    init {
        println("I am new human")
    }

    open fun running() {
        println("Running is so tired")
    }
}

class Korean: Human("seungjae") {
    override fun running() {
        super.running()
        println("아 힘들다")
    }
}