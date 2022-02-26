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