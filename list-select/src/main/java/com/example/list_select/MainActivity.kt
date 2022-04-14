package com.example.list_select

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.list_select.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<MyData>()
        for (i in 1..10) {
            datas.add(MyData(R.drawable.dog, "짱구", "나는 13살 페키니즈예요"))
        }
        myAdapter = MyAdapter(this, datas)

        binding.listview.apply {
            adapter = myAdapter
        }

        binding.listview.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            // 콜백매개변수는 순서대로 어댑터뷰, 해당 아이템의 뷰, 클릭한 순번, 항목의 아이디
            binding.listview.setSelection(i)
        }
    }
}