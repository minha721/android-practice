package com.example.rx_java

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Test
import java.util.concurrent.TimeUnit

class ExampleUnitTest2 {

    @Test
    fun defer_method(){
        val justSrc: Observable<Long> = Observable.just(System.currentTimeMillis())
        val deferSrc: Observable<Long> =
            Observable.defer { Observable.just(System.currentTimeMillis()) }

        println("#1 now = " + System.currentTimeMillis())
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        println("#2 now = " + System.currentTimeMillis())

        justSrc.subscribe { time -> println("#1 time = $time") }
        deferSrc.subscribe { time -> println("#2 time = $time") }
    }

    @Test
    fun empty_and_never_method() {
        Observable.empty<Any>()
            .doOnTerminate { println("empty 종료") }
            .subscribe { x: Any? -> println(x) }
        Observable.never<Any>()
            .doOnTerminate { println("never 종료") }
            .subscribe { x: Any? -> println(x) }
    }

    @Test
    fun interval_method() {
        val d: Disposable = Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { x: Long? -> println(x) }
        Thread.sleep(5000)
        d.dispose()
    }

    @Test
    fun range_method() {
        Observable.range(1,3)
            .subscribe{ x: Int -> println(x) }
    }

    @Test
    fun timer_method() {
        val src: Observable<*> = Observable.timer(3, TimeUnit.SECONDS)
        println("구독!")
        // 구독하면 3초 뒤에 아이템이 발행된다.
        src.subscribe { event: Any? -> println("실행!") }
        Thread.sleep(3000)
    }

    @Test
    fun map_method() {
        val intSrc = Observable.just(1, 2, 3)
        val tenIntSrc = intSrc.map { value: Int -> value * 10 }
        val strSrc = intSrc.map { value: Int -> value.toString() }
        tenIntSrc.subscribe { x -> println(x) }
        strSrc.subscribe { x -> println(x.javaClass.name) }
    }

    @Test
    fun flatMap_method() {
        val src = Observable.just("a", "b", "c")
        src.flatMap { str -> Observable.just(str+1, str+2) }
            .subscribe { x -> println(x)}
        src.subscribe { x -> println(x) }
    }

    @Test
    fun flatMap_gugudan() {
        Observable.range(2, 8)
            .flatMap { x: Int ->
                Observable.range(1, 9)
                    .map { y: Int ->
                        String.format("%d*%d=%d", x, y, x * y)
                    }
            }
            .subscribe { x: String? -> println(x) }
    }

    @Test
    fun buffer_method() {
        Observable.range(0, 10)
            .buffer(3)
            .subscribe { integers: List<Int> ->
                println("버퍼 데이터 발행")
                println(integers)
            }
    }

    @Test
    fun scan_method() {
        Observable.just("a", "b", "c", "d", "e")
            .scan { x: String, y: String -> x + y }
            .subscribe { x: String? -> println(x) }
    }

    @Test
    fun groupBy_method() {
        Observable.just(
            "Magenta Circle",
            "Cyan Circle",
            "Yello Triangle",
            "Magenta Triangle",
            "Yello Circle",
            "Cyan Triangle")
            .groupBy { item -> getShape(item) }
            .subscribe { group ->
                group.subscribe { shape ->
                    println("group : ${group.key}, value : ${shape}")
                }
            }
    }

    private fun getShape(shape: String): String {
        if (shape.contains("Circle")) {
            return "C"
        } else if (shape.contains("Triangle")) {
            return "T"
        }
        return "None"
    }
}