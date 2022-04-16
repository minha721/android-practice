package com.example.rx_java

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.reactivestreams.Publisher
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


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

    @Test
    fun create_method() {
        val source: Observable<String> = Observable.create { emitter ->
            emitter.onNext("Hello")
            emitter.onNext("World")
            emitter.onComplete()
//            emitter.onNext("Minha")
        }
        // Consumer를 통해 구독
        source.subscribe(System.out::println)
    }

    @Test
    fun create_method_error() {
        val source: Observable<String> = Observable.create { emitter ->
            emitter.onNext("Hello")
            emitter.onError(Throwable())
            emitter.onNext("World")
        }
        // Consumer를 통해 구독
        source.subscribe({ x: String? -> println(x) }) {
            println("Error!")
        }
    }

    @Test
    fun just_method() {
        val source: Observable<String> = Observable.just("Hello", "World")
        source.subscribe(System.out::println)
    }

    @Test
    fun fromArray_method() {
        val itemArray = arrayOf("A", "B", "C")
        val source = Observable.fromArray(*itemArray)
        source.subscribe(System.out::println)
    }

    @Test
    fun fromIterable_method() {
        val itemList = ArrayList<Any>()
        itemList.add("A")
        itemList.add("B")
        itemList.add("C")
        val source = Observable.fromIterable(itemList)
        source.subscribe(System.out::println)
    }

    @Test
    fun fromFuture_method() {
        val future = Executors.newSingleThreadExecutor().submit<String> {
                Thread.sleep(5000)
                "Hello World"
            }

        println("Hello Minha")
        val source = Observable.fromFuture(future)
        source.subscribe(System.out::println)
        println("I was blocked!")  // 블로킹됨
    }

    @Test
    fun fromPublisher_method() {
        val publisher: Publisher<String> = Publisher<String> {
                it.onNext("A")
                it.onNext("B")
                it.onNext("C")
                it.onComplete()
            }
        val source: Observable<String> = Observable.fromPublisher(publisher)
        source.subscribe(System.out::println)
    }

    @Test
    fun fromCallable_method() {
        val callable: Callable<String> = Callable<String> { "Hello World" }
        val source: Observable<String> = Observable.fromCallable(callable)
        source.subscribe(System.out::println)
    }

    @Test
    fun single_just() {
        Single.just("Hello World").subscribe(System.out::println)
    }

    @Test
    fun single_onSuccess() {
        // it: SingleEmitter<String>
        Single.create<String> { it.onSuccess("Hello") }.subscribe {
                it -> println(it)
        }
    }

    @Test
    fun observable_to_single() {
        val src = Observable.just(1, 2, 3)

        val singleSrc1 = src.all { i: Int -> i > 0 }
        val singleSrc2 = src.first(-1)
        val singleSrc3 = src.toList()

        singleSrc1.subscribe(System.out::println)
        singleSrc2.subscribe(System.out::println)
        singleSrc3.subscribe(System.out::println)
    }

    @Test
    fun single_to_observable() {
        val singleSrc = Single.just("Hello World")
        val observableSrc = singleSrc.toObservable()

        observableSrc.subscribe(System.out::println)
    }

    @Test
    fun maybe_ex() {
        Maybe.create { emitter: MaybeEmitter<Any?> ->
            emitter.onSuccess(100)
            emitter.onComplete() // 무시됨
        }
            .doOnSuccess { item: Any? -> println("doOnSuccess1") }
            .doOnComplete { println("doOnComplete1") }
            .subscribe { x: Any? -> println(x) }

        Maybe.create { emitter: MaybeEmitter<Any?> -> emitter.onComplete() }
            .doOnSuccess { item: Any? -> println("doOnSuccess2") }
            .doOnComplete { println("doOnComplete2") }
            .subscribe { x: Any? -> println(x) }
    }

    @Test
    fun observable_to_maybe() {
        val src1 = Observable.just(1, 2, 3)
        val srcMaybe1 = src1.firstElement()
        srcMaybe1.subscribe { x: Int? -> println(x) }

        val src2 = Observable.empty<Int>()
        val srcMaybe2 = src2.firstElement()
        srcMaybe2.subscribe(
            { x: Int? -> println(x) },
            { throwable: Throwable? -> }
        ) { println("onComplete") }
    }

    @Test
    fun completable_ex() {
        Completable.create { emitter: CompletableEmitter ->
            // do something here
            emitter.onComplete()
        }.subscribe { println("completed1") }

        Completable.fromRunnable {}.subscribe { println("completed2") }
    }

    @Test
    fun simple_cold_observable() {
        val src = Observable.interval(1, TimeUnit.SECONDS)
        src.subscribe { value -> println("#1: $value") }
        Thread.sleep(3000)
        src.subscribe { value -> println("#2: $value") }
        Thread.sleep(3000)
    }

    @Test
    fun simple_hot_observable() {
        val src = Observable.interval(1, TimeUnit.SECONDS).publish()
        src.connect()
        src.subscribe()
        src.subscribe { value -> println("#1: $value") }
        Thread.sleep(3000)
        src.subscribe { value -> println("#2: $value") }
        Thread.sleep(3000)
    }
}