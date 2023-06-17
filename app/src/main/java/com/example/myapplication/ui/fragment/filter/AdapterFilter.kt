package com.example.myapplication.ui.fragment.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemFilBinding

class AdapterFilter : RecyclerView.Adapter<AdapterFilter.ViewHolder>(){

    private var tasks = arrayListOf<Filter>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(tasks:List<Filter>){
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()

    }

    inner class ViewHolder(private val binding : ItemFilBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(task: Filter) {
            binding.ivIcon.setImageResource(task.img)
            binding.tvTitle.text = task.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFilBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(tasks[position])
    }
} //Переписать на LitAdapter