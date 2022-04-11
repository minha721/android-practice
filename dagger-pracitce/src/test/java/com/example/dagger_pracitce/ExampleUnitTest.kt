package com.example.dagger_pracitce

import com.example.dagger_pracitce.binding.DaggerNoStrComponent
import com.example.dagger_pracitce.binding.DaggerStrComponent
import com.example.dagger_pracitce.binding.Foo
import com.example.dagger_pracitce.lazy.Counter
import com.example.dagger_pracitce.lazy.DaggerCounterComponent
import com.example.dagger_pracitce.map_multibinding.DaggerMapComponent
import com.example.dagger_pracitce.named.DaggerStringComponent
import com.example.dagger_pracitce.named.MyString
import com.example.dagger_pracitce.person.DaggerPersonComponent
import com.example.dagger_pracitce.person.PersonB
import com.example.dagger_pracitce.provider.DaggerPCounterComponent
import com.example.dagger_pracitce.provider.PCounter
import com.example.dagger_pracitce.set_multibinding.DaggerSetComponent
import com.example.dagger_pracitce.simple.DaggerMyComponent
import com.example.dagger_pracitce.singleton.DaggerSMyComponent
import com.example.dagger_pracitce.subcomponent.Cafe
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
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

    @Test
    fun testNamed() {
        val myString = MyString()
        DaggerStringComponent.create().inject(myString)
        println(myString.strHello)
        println(myString.strWorld)
    }

    @Test
    fun testSingleton() {
        val myComponent = DaggerSMyComponent.create()
        val temp1 = myComponent.getObject()
        val temp2 = myComponent.getObject()

        assertSame(temp1, temp2)
    }

    @Test
    fun testBindings() {
        val foo = Foo()

        DaggerStrComponent.create().inject(foo)
        println(foo.str!!.isPresent)
        println(foo.str!!.get())

        DaggerNoStrComponent.create().inject(foo)
        println(foo.str!!.isPresent)
        println(foo.str!!.get())
    }

    @Test
    fun testSetMultiBinding() {
        val foo = com.example.dagger_pracitce.set_multibinding.Foo()
        DaggerSetComponent.create().inject(foo)
        foo.print()
    }

    @Test
    fun testMapMultiBinding() {
        val component = DaggerMapComponent.create()
        val lVal = component.getLongsByString?.get("foo")!!
        val sVal = component.getStringsByClass?.get(Foo::class.java)

        println(lVal)
        println(sVal)
    }

    @Test
    fun testSubComponent() {
        val cafe = Cafe()
        println(cafe.orderCoffee())
        println(cafe.orderCoffee())
        println(cafe.orderCoffee())
    }
}