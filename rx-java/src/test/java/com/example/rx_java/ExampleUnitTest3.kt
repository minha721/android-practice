package com.example.rx_java

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.*
import org.junit.Test
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger


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

    @Test
    fun pc_balance_ex() {
        Observable.range(1, 1000)
            .map { item: Int ->
                println("아이템 발행 : $item")
                item
            }
            .subscribe { item: Int -> println("아이템 소비 : $item") }
        sleep(1000)
    }

    @Test
    fun pc_unbalance_ex() {
        Observable.range(1, 10000)
            .map { item: Int ->
                println("아이템 발행 : $item")
                item
            }
            .observeOn(Schedulers.io())
            .subscribe { item: Int ->
                println("아이템 소비 : $item")
                sleep(100)
            }
        sleep(10 * 1000)
    }

    @Test
    fun flowable_ex() {
        Flowable.range(1, 10000)
            .map { item: Int ->
                println("아이템 발행 : $item")
                item
            }
            .observeOn(Schedulers.io())
            .subscribe { item: Int ->
                sleep(100)
                println("아이템 소비 : $item")
            }
        sleep(10 * 1000)
    }

    @Test
    fun flowable_exception_ex() {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.io())
            .map { item: Long ->
                sleep(2000)
                println("아이템 발행 : $item")
                item
            }
            .subscribe(
                { item: Long -> println("아이템 소비 : $item") }
            ) { throwable: Throwable -> throwable.printStackTrace() }
        sleep((30 * 1000).toLong())
    }

    @Test
    fun onBackPressureBuffer_method() {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
            .onBackpressureBuffer()
            .observeOn(Schedulers.io())
            .map { item: Long ->
                sleep(2000)
                println("아이템 발행 : $item")
                item
            }
            .subscribe(
                { item: Long -> println("아이템 소비 : $item") }
            ) { throwable: Throwable -> throwable.printStackTrace() }
        sleep((30 * 1000).toLong())
    }

    @Test
    fun onBackPressureLatest_method() {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
            .onBackpressureLatest()
            .observeOn(Schedulers.io())
            .subscribe({ item: Long ->
                sleep(100)
                println("아이템 소비 : $item") })
            { throwable: Throwable -> throwable.printStackTrace() }

        sleep((30 * 1000).toLong())
    }

    @Test
    fun onBackPressureDrop_method() {
        Flowable.range(1, 300)
            .onBackpressureDrop()
            .observeOn(Schedulers.io())
            .subscribe({ item: Int ->
                Thread.sleep(10)
                println("아이템 소비 : $item") })
            { throwable: Throwable -> throwable.printStackTrace() }
        sleep((30 * 1000).toLong())
    }

    @Test
    fun onBackPressureDrop_dropCallback_ex() {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
            .onBackpressureDrop { item: Long ->
                println("아이템 버림 : $item")
            }
            .observeOn(Schedulers.io())
            .subscribe({ item: Long ->
                sleep(100)
                println("아이템 소비 : $item") })
            { throwable: Throwable -> throwable.printStackTrace() }
        sleep((30 * 1000).toLong())
    }

    @Test
    fun backpressureStrategy_ex() {
        Flowable.create(FlowableOnSubscribe { emitter: FlowableEmitter<Int> ->
            for (i in 0..1000) {
                if (emitter.isCancelled) {
                    return@FlowableOnSubscribe
                }
                emitter.onNext(i)
            }
            emitter.onComplete()
        } as FlowableOnSubscribe<Int>, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
            .subscribe({ x -> println(x) }) { t: Throwable -> t.printStackTrace() }
        sleep(100)
    }

    @Test
    fun publishSubject_ex() {
        val src = PublishSubject.create<Any>()
        src.subscribe(
            { item -> println("A : $item") }, { t -> t.printStackTrace() }
        ) { println("A: onComplete") }
        src.subscribe(
            { item -> println("B : $item") }, { t -> t.printStackTrace() }
        ) { println("B: onComplete") }
        src.onNext("Hello")
        src.onNext("World")
        src.onNext("!!!")
        src.onComplete()
    }

    @Test
    fun publishSubject_ex2() {
        val src1 = Observable.interval(1, TimeUnit.SECONDS)
        val src2 = Observable.interval(500, TimeUnit.MILLISECONDS)
        val subject = PublishSubject.create<Any>()

        src1.map { item -> "A: $item" }.subscribe(subject)
        src2.map { item -> "B: $item" }.subscribe(subject)
        subject.subscribe{x -> println(x)}
        sleep(5000)
    }

    @Test
    fun serializedSubject_ex() {
        val counter = AtomicInteger()
        val subject = PublishSubject.create<Any>().toSerialized()
        subject.doOnNext { i -> counter.incrementAndGet() }
            .doOnNext { i -> counter.decrementAndGet() }
            .filter { i -> counter.get() !== 0 }
            .subscribe(System.out::println) { throwable -> throwable.printStackTrace() }
        val runnable = Runnable {
            for (i in 0..99999) {
                try {
                    sleep(1)
                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                }
                subject.onNext(i)
            }
        }
        Thread(runnable).start()
        Thread(runnable).start()
        sleep(1000)
        println("종료")
    }

    @Test
    fun behaviorSubject_ex() {
        val subject = BehaviorSubject.create<Any>()
        subject.subscribe{item -> println("A: $item")}
        subject.onNext(1)
        subject.onNext(2)
        subject.subscribe{item -> println("B: $item")}
        subject.onNext(3)
        subject.subscribe{item -> println("C: $item")}
    }

    @Test
    fun replaySubject_ex() {
        val subject = ReplaySubject.create<Any>()
        subject.subscribe{item -> println("A: $item")}
        subject.onNext(1)
        subject.onNext(2)
        subject.subscribe{item -> println("B: $item")}
        subject.onNext(3)
        subject.subscribe{item -> println("C: $item")}
    }

    @Test
    fun asyncSubject_ex() {
        val subject = AsyncSubject.create<Any>()
        subject.subscribe{item -> println("A: $item")}
        subject.onNext(1)
        subject.onNext(2)
        subject.subscribe{item -> println("B: $item")}
        subject.onNext(3)
        subject.onComplete()
        subject.subscribe{item -> println("C: $item")}
    }

    @Test
    fun unicastSubject_ex() {
        val subject: Subject<Long> = UnicastSubject.create()
        Observable.interval(1, TimeUnit.SECONDS).subscribe(subject)
        sleep(3000)
        subject.subscribe { i -> println("A: $i") }
        sleep(2000)
    }
}