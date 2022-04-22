package com.example.rx_java

import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
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

    @Test
    fun debounce_method() {
        Observable.create { emitter: ObservableEmitter<Any?> ->
            emitter.onNext("1")
            Thread.sleep(110)
            emitter.onNext("2")
            emitter.onNext("3")
            emitter.onNext("4")
            emitter.onNext("5")
            Thread.sleep(90)
            emitter.onNext("6")
        }
            .debounce(100, TimeUnit.MILLISECONDS)
            .subscribe { x: Any? -> println(x) }

        Thread.sleep(300)
    }

    @Test
    fun distinct_method() {
        Observable.just(1,2,2,1,3)
            .distinct()
            .subscribe{ x -> println(x) }
    }

    @Test
    fun elementAt_method() {
        Observable.just(1,2,3,4)
            .elementAt(2)
            .subscribe{ x -> println(x) }
    }

    @Test
    fun filter_method() {
        Observable.just(2,30,22,5,60,1)
            .filter{ x -> x>10 }
            .subscribe{ x -> println(x) }
    }

    @Test
    fun sample_method() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
            .sample(300, TimeUnit.MILLISECONDS)
            .subscribe{ x -> println(x) }
        Thread.sleep(1000)
    }

    @Test
    fun skip_method() {
        Observable.just(1,2,3,4)
            .skip(2)
            .subscribe{ x -> println(x) }
    }

    @Test
    fun take_method() {
        Observable.just(1,2,3,4)
            .take(2)
            .subscribe{ x -> println(x) }
    }

    @Test
    fun all_method() {
        Observable.just(1,2,3)
            .all{ integer -> integer>0 }
            .subscribe{ x -> println(x) }

        Observable.just(0,1,2,3)
            .all{ integer -> integer>0 }
            .subscribe{ x -> println(x) }
    }

    @Test
    fun amb_method() {
        val list: ArrayList<Observable<Int>> = ArrayList()
        list.add(Observable.just(20, 40, 60)
                .delay(100, TimeUnit.MILLISECONDS))
        list.add(Observable.just(1, 2, 3))
        list.add(Observable.just(0, 0, 0)
                .delay(200, TimeUnit.MILLISECONDS))
        Observable.amb<Any>(list).subscribe { x -> println(x) }
    }

    @Test
    fun combineLatest_method() {
        val src1 = Observable.create { emitter: ObservableEmitter<Int> ->
            Thread {
                for (i in 1..5) {
                    emitter.onNext(i)
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }

        val src2 = Observable.create { emitter: ObservableEmitter<String> ->
            Thread {
                try {
                    Thread.sleep(500)
                    emitter.onNext("A")
                    Thread.sleep(700)
                    emitter.onNext("B")
                    Thread.sleep(100)
                    emitter.onNext("C")
                    Thread.sleep(700)
                    emitter.onNext("D")
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }.start()
        }

        Observable.combineLatest(src1, src2) { num: Int, str: String -> num.toString() + str }
            .subscribe { x: String? -> println(x) }

        Thread.sleep(5000)
    }

    @Test
    fun zip_method() {
        val src1 = Observable.create { emitter: ObservableEmitter<Int> ->
            Thread {
                for (i in 1..5) {
                    emitter.onNext(i)
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }

        val src2 = Observable.create { emitter: ObservableEmitter<String> ->
            Thread {
                try {
                    Thread.sleep(500)
                    emitter.onNext("A")
                    Thread.sleep(700)
                    emitter.onNext("B")
                    Thread.sleep(100)
                    emitter.onNext("C")
                    Thread.sleep(700)
                    emitter.onNext("D")
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }.start()
        }

        Observable.zip(src1, src2) { num: Int, str: String -> num.toString() + str }
            .subscribe { x: String? -> println(x) }

        Thread.sleep(5000)
    }

    @Test
    fun merge_method() {
        val src1 = Observable.intervalRange(1, 5, 0, 100, TimeUnit.MILLISECONDS)
            .map { value: Long -> value * 20 }

        val src2 = Observable.create { emitter: ObservableEmitter<Int> ->
            Thread {
                try {
                    Thread.sleep(350)
                    emitter.onNext(1)
                    Thread.sleep(200)
                    emitter.onNext(1)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }.start()
        }

        Observable.merge(src1, src2)
            .subscribe { x -> println(x) }

        Thread.sleep(1000)
    }

    @Test
    fun error_example() {
        Observable.just("1", "2", "a", "3")
            .map { i: String -> i.toInt() }
            .subscribe { x -> println(x) }
    }

    @Test
    fun error_example_2() {
        Observable.just("1", "2", "a", "3")
            .map { i: String -> i.toInt() }
            .subscribe({ x: Int? -> println(x) })
            { t: Throwable? -> println("Error") }
    }

    @Test
    fun onErrorReturn_method() {
        Observable.just("1", "2", "a", "3")
            .map { i: String -> i.toInt() }
            .onErrorReturn { throwable -> -1 }
            .subscribe { x: Int? -> println(x) }
    }

    @Test
    fun onErrorResumeNext_method() {
        Observable.just("1", "2", "a", "3")
            .map { i: String -> i.toInt() }
            .onErrorResumeNext { throwable -> Observable.just(100, 200, 300) }
            .subscribe { x -> println(x) }
    }

    @Test
    fun retry_method() {
        Observable.just("1", "2", "a", "3")
            .map { i: String -> i.toInt() }
            .retry()
            .subscribe { x -> println(x) }

    }

    @Test
    fun doOnEach_method() {
        Observable.just(1, 2, 3).doOnEach { notification: Notification<Int> ->
            val i = notification.value
            val isOnNext = notification.isOnNext
            val isOnComplete = notification.isOnComplete
            val isOnError = notification.isOnError
            val throwable = notification.error
            println("i = $i")
            println("isOnNext = $isOnNext")
            println("isOnComplete = $isOnComplete")
            println("isOnError = $isOnError")
            throwable?.printStackTrace()
        }.subscribe { value -> println("Subscribed = $value")}
    }

    @Test
    fun doOnNext_method() {
        Observable.just(1, 2, 3).doOnNext { item: Int ->
            require(item <= 1)
        }.subscribe({ x: Int? -> println(x) }) { throwable: Throwable -> throwable.printStackTrace() }
    }

    @Test
    fun doOnSubscribe_method() {
        Observable.just(1, 2, 3).doOnSubscribe { disposable: Disposable? ->
                println("구독 시작")
        }.subscribe { x: Int? -> println(x) }
    }

    @Test
    fun doOnComplete_method() {
        Observable.just(1, 2, 3).doOnComplete {
            println("완료")
        }.subscribe { x: Int? -> println(x) }
    }

    @Test
    fun doOnError_method() {
        Observable.just(2, 1, 0)
            .map { i: Int -> 10 / i }
            .doOnError { throwable: Throwable? ->
                println("오류")
            }.subscribe({ x: Int? -> println(x) }) { t: Throwable -> t.printStackTrace() }
    }

}