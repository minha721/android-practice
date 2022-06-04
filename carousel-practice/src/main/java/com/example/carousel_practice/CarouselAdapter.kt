package com.example.carousel_practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carousel_practice.databinding.RecyclerCarouselItemBinding

class CarouselViewHolder(val binding: RecyclerCarouselItemBinding): RecyclerView.ViewHolder(binding.root)
class CarouselAdapter(var datas: ArrayList<CarouselData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarouselViewHolder(RecyclerCarouselItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as CarouselViewHolder).binding

        binding.ivImage.setImageResource(datas[position].img)
        binding.tvName.text = datas[position].name
    }
}