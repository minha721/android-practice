package com.example.dagger_pracitce

import com.example.dagger_pracitce.lazy.Counter
import com.example.dagger_pracitce.lazy.DaggerCounterComponent
import com.example.dagger_pracitce.person.DaggerPersonComponent
import com.example.dagger_pracitce.person.PersonB
import com.example.dagger_pracitce.provider.DaggerPCounterComponent
import com.example.dagger_pracitce.provider.PCounter
import com.example.dagger_pracitce.simple.DaggerMyComponent
import org.junit.Test

import org.junit.Assert.*

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
    fun testSimpleExample() {
        val component = DaggerMyComponent.create()
        println("result = ${component.getString()}")
    }

    @Test
    fun testInjection() {
        val personComponent = DaggerPersonComponent.create()

        val personA = personComponent.getPersonA()
        println("${personA.name} : ${personA.age}")

        val personB = PersonB()
        DaggerPersonComponent.create().inject(personB)
        println("${personB.name} : ${personB.age}")
    }

    @Test
    fun testLazyInjection() {
        val counterComponent = DaggerCounterComponent.create()
        val counter = Counter()
        counterComponent.inject(counter)
        counter.printLazy()
    }

    @Test
    fun testProviderInjection() {
        val providerComponent = DaggerPCounterComponent.create()
        val counter = PCounter()
        providerComponent.inject(counter)
        counter.printProvider()
    }
}