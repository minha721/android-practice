package com.example.rx_java

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun impertive_programming() {
        var items = ArrayList<Int>()
        items.add(1)
        items.add(2)
        items.add(3)
        items.add(4)

        // 짝수만 출력
        for (item in items) {
            if (item % 2 == 0) {
                println(item)
            }
        }

        items.add(5)
        items.add(6)
        items.add(7)
        items.add(8)
    }

    @Test
    fun reactive_programming() {
        val items = PublishSubject.create<Int>()
//        val items = ReplaySubject.create<Int>()

        items.onNext(1)
        items.onNext(2)
        items.onNext(3)
        items.onNext(4)

        // 짝수만 출력
        items.filter { item: Int -> item % 2 == 0 }
            .subscribe { x: Int? -> println(x) }

        items.onNext(5)
        items.onNext(6)
        items.onNext(7)
        items.onNext(8)
    }
}