package com.example.kotlin_practice

fun main() {
    print("1. fun helloWorld() : ")
    helloWorld()
    println("2. fun add() : ${add(2, 3)}")
    print("3. fun variable1() : ")
    variable1()
    println("4. fun variable2() : ")
    variable2()
    println("5. fun variable3() : ")
    variable3()
    println("6. fun arrayAndList() : ")
    arrayAndList()
    print("7. checkScore() : ")
    checkScore(70)
    println("8. range() : ")
    range()
    print("9. oddNumbers() : ")
    oddNumbers()
    print("10. evenNumbersDown() : ")
    evenNumbersDown()
    print("11. evenNumbersDown2() : ")
    evenNumbersDown2()
    println("12. maxBy() : ${maxBy(5, 9)}")
    println("13. maxBy2() : ${maxBy2(7, 3)}")
    print("14. checkScore2() : ")
    checkScore2("D")
    print("15. sumFor() : ")
    sumFor()
    println("16. indexWhile() : ")
    indexWhile()
    print("17. withIndexFor() : ")
    withIndexFor()
}

fun helloWorld() {
    println("Hello World!")
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun variable1() {
    // var로 선언한 변수는 값을 다시 지정하거나 초기화를 나중에 할 수 있다
    var name1: String
    name1 = "kotlin"

    // val로 선언한 변수는 변경할 수 없다
    val name2 = "kotlin"

    println("var name1 is ${name1}, val name2 is ${name2}")
}

fun variable2() {
    var int: Int = 123
    var long: Long = 123456L
    var double: Double = 12.34
    var float: Float = 12.34F

    println("int is $int")
    println("long is $long")
    println("double is $double")
    println("float is $float")
}

fun variable3() {
    var str: String = "코틀린입니다"
    var chr: Char = '코'
    var longStr: String = """안녕하세요
    코틀린입니다
    문자열 여러줄 출력이에요
    """

    println("str is $str")
    println("chr is $chr")
    println("longStr is $longStr")
}

fun arrayAndList() {
    val myArray: Array<Any> = arrayOf(1, "b", 3)
    val myList: List<Any> = listOf(2, "c", 4, 5)
    // 선언과 동시에 객체를 할당하는 경우
    // arrayList의 값은 변하지만 참조값은 변하지 않아 val로 지정 가능
    val myArrayList = arrayListOf<Any>()

    // array의 경우 사이즈 수정 불가능, 값 수정 가능
    myArray[0] = 7

    // list의 경우 사이즈 수정 불가능, 값 수정 불가능 (읽기 전용)
    var result = myList.get(1)

    // arrayList의 경우 사이즈 수정 가능, 값 수정 가능
    myArrayList.add(10)
    myArrayList.add(15)

    println("array is {$myArray}")
    println("list is {$myList}, list[1] is {$result}")
    println("arrayList is {$myArrayList}")
}

fun checkScore(score: Int) {
    when(score) {
        in 90..100 -> println("Excellent")
        in 70 until 90 -> println("Great")
        in 40 until 70 -> println("Not bad")
        else -> println("Fighting")
    }
}

fun range() {
    val rangeTo = 10.rangeTo(20)
    val countingDown = 100.downTo(50)

    println("rangeTo : $rangeTo")
    println("countingDown : $countingDown")
}

fun oddNumbers() {
    val oneToFifty = 1..50
    val oddNumbers = oneToFifty.step(2)

    println("oddNumbers : $oddNumbers")
}

fun evenNumbersDown() {
    val fiftyToZero = 50.downTo(0)
    val evenNumbersDown = fiftyToZero.step(2)

    println("evenNumbersDown : $evenNumbersDown")
}

fun evenNumbersDown2() {
    val evenNumbersDown2 = (0..50).step(2).reversed()

    println("evenNumbersDown2 : $evenNumbersDown2")
}

fun maxBy(a: Int, b: Int): Int {
    if(a>b) {
        return a
    } else {
        return b
    }
}

fun maxBy2(a: Int, b: Int): Int = if(a>b) a else b

fun checkScore2(score: String) {
    when(score) {
        "A" -> println("score is A")
        "B" -> println("score is B")
        "C", "D", "F" -> println("score is under B")
        else -> println("I don't know")
    }
}

fun sumFor() {
    var sum = 0
    for(i in 1..10 step 2) {
        sum += i
    }
    println(sum)
}

fun indexWhile() {
    var index = 0

    while(index < 10) {
        println("index : ${index}")
        index++
    }
}

fun withIndexFor() {
    val students = arrayListOf("이민하", "오승재", "이짱구")

    for((index, name) in students.withIndex()){
        println("${index+1}번째 학생 : ${name}")
    }
}