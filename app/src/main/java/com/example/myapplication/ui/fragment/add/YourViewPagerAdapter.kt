package com.example.myapplication.ui.fragment.add

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemImageBinding

class YourViewPagerAdapter(private val context: Context) : RecyclerView.Adapter<YourViewPagerAdapter.ImageViewHolder>() {
    private val images: MutableList<Bitmap> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.bindImage(image)
    }

    override fun getItemCount(): Int = images.size

    fun addImage(image: Bitmap) {
        images.add(image)
        notifyItemInserted(images.size - 1)
    }

    fun addImages(newImages: List<Bitmap>) {
        images.addAll(newImages)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView: ImageView = binding.ivImage

        fun bindImage(image: Bitmap) {
            imageView.setImageBitmap(image)
        }
    }
}
