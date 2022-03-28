package com.example.mvvm_practice

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_practice.databinding.RecyclerMovieItemBinding

class MovieViewHolder(val binding: RecyclerMovieItemBinding): RecyclerView.ViewHolder(binding.root)

class MovieAdapter(val datas: List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return MovieViewHolder(RecyclerMovieItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MovieViewHolder).binding

        Glide.with(context).load(datas[position].poster).into(binding.ivPoster)
        binding.tvRate.text = (position + 1).toString()
        binding.tvTitle.text = datas[position].title
        binding.tvDirector.text = datas[position].director
        binding.tvDescription.text = datas[position].description

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", datas[position].id)
            context.startActivity(intent)
        }
    }
}