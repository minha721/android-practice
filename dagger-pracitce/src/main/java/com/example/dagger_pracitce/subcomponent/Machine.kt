package com.example.dagger_pracitce.subcomponent

class Machine {
    lateinit var component: MachineComponent

    constructor(builder: MachineComponent.Builder){ // 매개변수로 서브 컴포넌트로부터 빌더를 제공 받음
        component = builder.setMachineModule(MachineModule()).build()
        component.inject(this)
    }

    fun extract(): Coffee {
        return component.getCoffee()
    }
}