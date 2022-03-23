package com.example.kotlin_practice

fun main() {
    println("1. lambda square : ${square(12)}")
    println("2. lambda nameAge : ${nameAge("minha", 25)}")
    println("3. extension function : ")
    val name = "minha"
    println(name.introduce(25))
    println("4. lambda return : ${grade(98)}")
    println("5. data class movie : ${movie()}")
    print("6. companion object student : ")
    student()
}

val square: (Int) -> (Int) = {number -> number*number}

val nameAge = {name: String, age: Int ->
    "My name is ${name}. I'm ${age}."
}

val introduce: String.(Int) -> String = {
    "My name is ${this}. I'm ${it}."
}

val grade: (Int) -> (String) = {
    when(it) {
        in 0..40 -> "Fail"
        in 41..70 -> "Pass"
        in 71..100 -> "Great"
        else -> "Error"
    }
}

data class Movie(val name: String, val genre: String, val director: String, val score: Float)

fun movie(): Movie {
    val movie = Movie("Frozen 2", "Animation", "크리스 벅", 8.95F)
    return movie
}

class Student private constructor(val id: Int, val name: String){
    companion object StudentInfo: IdProvider {

        override fun getId(): Int {
            return 12345
        }

        val name = "minha"
        fun create() = Student(getId(), name)
    }
}

interface IdProvider {
    fun getId(): Int
}

fun student() {
    val student = Student.StudentInfo.create()
    val studentId = Student.StudentInfo.getId()

    println("Id is ${studentId}. Name is ${student.name}.")
}
