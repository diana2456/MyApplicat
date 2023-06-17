package com.example.myapplication.ui.fragment.about_us

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemAdoutUsBinding

class Adapter : ListAdapter<ModelUs,
        Adapter.ViewHolder>(DiffUtilNoteItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
           ItemAdoutUsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemAdoutUsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: ModelUs) {
            binding.desc.text = note.desc
            binding.ivStar.setImageResource(note.star)
            binding.tvName.text = note.name
        }
    }

    private class DiffUtilNoteItemCallback : DiffUtil.ItemCallback<ModelUs>() {
        override fun areItemsTheSame(oldItem: ModelUs, newItem: ModelUs): Boolean {
            return oldItem.star == newItem.star
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ModelUs, newItem: ModelUs): Boolean {
            return oldItem == newItem
        }
    }
}