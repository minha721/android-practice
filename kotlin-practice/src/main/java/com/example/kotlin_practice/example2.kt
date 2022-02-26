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