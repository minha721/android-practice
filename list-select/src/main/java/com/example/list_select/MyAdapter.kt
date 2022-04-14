package com.example.list_select

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(val context: Context, val myData: List<MyData>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, null)

        val img = view.findViewById<ImageView>(R.id.iv_image)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val des = view.findViewById<TextView>(R.id.tv_description)

        val data = myData[position]
        img.setImageResource(data.img)
        name.text = data.name
        des.text = data.description

        return view
    }

    override fun getCount(): Int {
        return myData.size
    }

    override fun getItem(p0: Int): Any {
        return myData[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }
}