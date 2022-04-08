package com.example.mvvm_practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mvvm_practice.databinding.RecyclerMovieItemBinding
import com.example.mvvm_practice.models.GhibliItem

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    inner class MyViewHolder (val binding: RecyclerMovieItemBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<GhibliItem>() {
        override fun areItemsTheSame(oldItem: GhibliItem, newItem: GhibliItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GhibliItem, newItem: GhibliItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movies: List<GhibliItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerMovieItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curMovie = movies[position]

        holder.binding.apply {
            tvTitle.text = curMovie.title
            tvRate.text = curMovie.rt_score
            tvDirector.text = curMovie.director
            tvDescription.text = curMovie.description
            ivPoster.load(curMovie.image)

        }
    }
}