package com.example.myapplication.ui.fragment.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FavoriteItenBinding
import com.example.myapplication.entity.Favorite
import com.example.myapplication.room.FavDB
import com.google.firebase.database.*

class FavoriteAdapter(private val context: Context, private val favItemList: MutableList<Favorite>) :
    ListAdapter<Favorite, FavoriteAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var favDB: FavDB
    private lateinit var refLike: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favDB = FavDB(context)
        return ViewHolder(FavoriteItenBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = favItemList[position].tvTitle
        holder.photo.setImageResource(favItemList[position].image)
        holder.dil.text = favItemList[position].tvDil
        holder.km.text = favItemList[position].tvKm
        holder.location.text = favItemList[position].tvLocation
        holder.san.text = favItemList[position].tvSan
        holder.room.text = favItemList[position].tvRoom
    }

    override fun getItemCount(): Int {
        return favItemList.size
    }

    inner class ViewHolder(binding: FavoriteItenBinding) : RecyclerView.ViewHolder(binding.root) {

        var photo: ImageView = binding.ivPhoto
        var title: TextView = binding.tvTitle
        var heart: ImageView = binding.heart
        var dil: TextView = binding.tvDil
        var room: TextView = binding.tvRoom
        var km: TextView = binding.tvKm
        var san: TextView = binding.tvSan
        var location: TextView = binding.tvLocation

        init {
            refLike = FirebaseDatabase.getInstance().reference.child("likes")
            heart.setOnClickListener {
                val position = adapterPosition
                val favItem = favItemList[position]
                val upvotesRefLike = refLike.child(favItem.id)
                favDB.removeFav(favItem.id)
                removeItem(position)
                upvotesRefLike.runTransaction(object : Transaction.Handler {
                    override fun doTransaction(mutableData: MutableData): Transaction.Result {
                        try {
                            val currentValue = mutableData.getValue(Int::class.java)
                            if (currentValue == null) {
                                mutableData.value = 1
                            } else {
                                mutableData.value = currentValue - 1
                            }
                        } catch (e: Exception) {
                            throw e
                        }
                        return Transaction.success(mutableData)
                    }

                    override fun onComplete(databaseError: DatabaseError?, b: Boolean, dataSnapshot: DataSnapshot?) {
                        println("Transaction completed")
                    }
                })
            }
        }
    }

    private fun removeItem(position: Int) {
        favItemList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favItemList.size)
    }

    class DiffCallback : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }
}
