package com.android.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.recycler.databinding.RecyclerItemBinding

class MyAdapter(val context: Context, val datas: MutableList<Dog>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }
    lateinit var onItemClickListener : OnItemClickListener

    inner class MyViewHolder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Dog) {
            binding.apply {
                val packName = context.packageName // 패키지명
                val resId = context.resources.getIdentifier(data.img, "drawable", packName)
                ivImage.setImageResource(resId)

                tvName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            bind(datas[position])

            itemView.setOnClickListener {
                onItemClickListener.onClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}