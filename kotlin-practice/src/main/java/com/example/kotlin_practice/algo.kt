package com.example.kotlin_practice

fun main() {
    println("twoDimenArray1 result")
    twoDimenArray1()
    println()

    println("twoDimenArray2 result")
    twoDimenArray2()
    println()

    println("when1 result")
    when1()
    println()

    println("when2 result")
    when2()
    println()

    println("string1 result")
    string1()
    println()

    println("string2 result")
    string2()
    println()

    println("string3 result")
    string3()
    println()
}

fun twoDimenArray1() {
    val array = arrayOf(
        arrayOf("a", "b", "c"),
        arrayOf("d", "e", "f"),
        arrayOf("g", "h", "i"))

    println("array : ${array.contentDeepToString()}")
    println("array[1][2] : ${array[1][2]}")
}

fun twoDimenArray2() {
    // i는 행번호, j는 열번호를 나타낸다.
    val array = Array(3){ i ->
        Array(4){ j ->
            i * 4 + j
        }
    }

    for ((i, row) in array.withIndex()) {
        for ((j, column) in row.withIndex()) {
            print("[$i, $j] = $column\t")
        }
        println()
    }
}

fun when1() {
    val value = "o"
    when(value) {
        "r" -> println("My color is Red")
        "o" -> println("My color is Orange")
        "y" -> println("My color is Yellow")
    }
}

fun when2() {
    val score = 87
    val grade = when(score) {
        in 90..100 -> "A" // 90~100
        in 80 until 90 -> "B" // 80~89
        in 70 until 80 -> "C" // 70~79
        else -> "F" // ~69
    }

    println("My score is $score, My grade is $grade")
}

fun string1() {
    val s = "Hello, Kotlin"

    println("s[0] : ${s[0]}")
    println("substring(3~8) : ${s.substring(3, 9)}")
    // 제일 먼저 나오는 t의 인덱스 반환, 대소문자 구분함
    println("t's index : ${s.indexOf('t')}")
}

fun string2() {
    val s = "Hello, My name is minha"

    val s1 = s.split(' ')
    println("s.split : $s1")

    val s2 = s1.joinToString(" ")
    println("s.joinToString : $s2")

    val s3 = s2.replace(' ', '-')
    println("s.replace : $s3")
}

fun string3() {
    val s = "Hello, Kotlin"

    // 대소문자 구분함
    println("s.startsWith(Hello) : ${s.startsWith("Hello")}")
    println("s.startsWith(lo) : ${s.startsWith("lo")}")
    println("s.endsWith(lin) : ${s.endsWith("lin")}")
    println("s.endsWith(otli) : ${s.endsWith("otli")}")
    println("s.contains(llo) : ${s.contains("llo")}")
}

