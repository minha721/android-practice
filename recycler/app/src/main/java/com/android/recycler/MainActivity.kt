package com.android.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.recycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    val myDatas = mutableListOf<Dog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in 1..10) {
            myDatas.add(Dog("dog1", "말티즈"))
            myDatas.add(Dog("dog2", "포메라니안"))
            myDatas.add(Dog("dog3", "페키니즈"))
            myDatas.add(Dog("dog4", "푸들"))
            myDatas.add(Dog("dog5", "치와와"))
            myDatas.add(Dog("dog6", "비숑"))
        }

        myAdapter = MyAdapter(this, myDatas)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = myAdapter
        }
    }
}