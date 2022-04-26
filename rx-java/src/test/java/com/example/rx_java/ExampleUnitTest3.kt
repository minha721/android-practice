package com.example.rx_java

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

class ExampleUnitTest3 {

    @Test
    fun schedulers() {
        val singleSchedulers = Schedulers.single()
        val io = Schedulers.io()
        val computation = Schedulers.computation()
        val trampoline = Schedulers.trampoline()
        val newThread = Schedulers.newThread()
        // 안드로이드용
        // val mainThread = AndroidSchedulers.mainThread()
    }

    @Test
    fun mainThread_ex() {
        val src = Observable.create<Int> { emitter ->
            for (i in 0..2) {
                val threadName = Thread.currentThread().name
                println("#발행 [$threadName] : $i")
                emitter.onNext(i)
                Thread.sleep(100)
            }
            emitter.onComplete()
        }
        src.subscribe { s ->
            val threadName = Thread.currentThread().name
            println("#구독 [$threadName] : $s")
        }
    }

    @Test
    fun subscribeOn_ex() {
        val src = Observable.create<Int> { emitter ->
            for (i in 0..2) {
                val threadName = Thread.currentThread().name
                println("#발행 [$threadName] : $i")
                emitter.onNext(i)
                Thread.sleep(100)
            }
            emitter.onComplete()
        }
        src.subscribeOn(Schedulers.io())
            .subscribe { s: Int ->
                val threadName = Thread.currentThread().name
                println("#구독 [$threadName] : $s")
            }
        Thread.sleep(500)
    }

    @Test
    fun subscribeOn_observeOn_ex() {
        val src = Observable.create<Int> { emitter ->
            for (i in 0..2) {
                val threadName = Thread.currentThread().name
                println("#발행 [$threadName] : $i")
                emitter.onNext(i)
                Thread.sleep(100)
            }
            emitter.onComplete()
        }
        src.observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .subscribe { s: Int ->
                val threadName = Thread.currentThread().name
                println("#구독 [$threadName] : $s")
            }
        Thread.sleep(500)
    }

    @Test
    fun scheduler_exception_ex() {
        Observable.interval(200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe { value: Long ->
                println("${Thread.currentThread().name} : ${value}")
            }
        sleep(1000)
    }
}