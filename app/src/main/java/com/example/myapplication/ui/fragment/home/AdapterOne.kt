package com.example.myapplication.ui.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTaskBinding
import com.example.myapplication.entity.RealEstate
import com.example.myapplication.room.FavDB
import com.google.firebase.database.*
import com.example.myapplication.R

class AdapterOne(private val context: Context) : ListAdapter<RealEstate, AdapterOne.ViewHolder>(
    DiffCallback()
) {

    private lateinit var favDB: FavDB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favDB = FavDB(context)
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)
        if (firstStart) {
            createTableOnFirstStart()
        }
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        val coffeeItem = getItem(position)
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val viewHolder = holder.binding
            readCursorData(coffeeItem, viewHolder, position)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coffeeItem = getItem(position)
        holder.bind(coffeeItem)
    }

    override fun getItemCount(): Int = currentList.size

    inner class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.heat.setOnClickListener {
                val position = adapterPosition
                val coffeeItem = getItem(position)
                coffeeItem?.let {
                    likeClick(it, binding.heat, binding.likeCountTextView)
                }
            }
        }

        fun bind(item: RealEstate) {
            with(binding) {
                ivPho.setImageResource(item.image)
                tvTit.text = item.tvTitle
                tvD.text = item.tvDil
                tvRo.text = item.tvRoom
                tvMd.text = item.tvKm
                tvSn.text = item.tvSan
                tvLocat.text = item.tvLocation
            }
            readCursorData(item, binding, adapterPosition)
        }
    }

    private fun createTableOnFirstStart() {
        favDB.insertEmpty()

        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }

    @SuppressLint("Range")
    private fun readCursorData(coffeeItem: RealEstate, viewHolder: ItemTaskBinding, position: Int) {
        val cursor = favDB.readAllData(coffeeItem.id)
        val db = favDB.readableDatabase
        try {
            while (cursor.moveToNext()) {
                val itemFavStatus = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS))
                coffeeItem.status = itemFavStatus

                if (itemFavStatus != null && itemFavStatus == "1") {
                    viewHolder.heat.setImageResource(R.drawable.heart_red)
                } else if (itemFavStatus != null && itemFavStatus == "0") {
                    viewHolder.heat.setImageResource(R.drawable.heart)
                }
            }
        } finally {
            cursor.close()
            db.close()
        }
    }

    private fun likeClick(coffeeItem: RealEstate, favBtn: ImageView, textLike: TextView) {
        val refLike = FirebaseDatabase.getInstance().reference.child("likes")
        val upvotesRefLike = refLike.child(coffeeItem.id)

        if (coffeeItem.status == "0") {
            coffeeItem.status = "1"
            favDB.insertIntoTheDatabase(
                coffeeItem.tvTitle,
                coffeeItem.image,
                coffeeItem.id,
                coffeeItem.status,
                coffeeItem.tvDil,
                coffeeItem.tvRoom,
                coffeeItem.tvSan,
                coffeeItem.tvLocation,
                coffeeItem.tvKm
            )
            favBtn.setImageResource(R.drawable.heart_red)
            favBtn.isSelected = true

            upvotesRefLike.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    try {
                        val currentValue = mutableData.getValue(Int::class.java)
                        if (currentValue == null) {
                            mutableData.value = 1
                        } else {
                            mutableData.value = currentValue + 1
                            Handler(Looper.getMainLooper()).post {
                                textLike.text = mutableData.value.toString()
                            }
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

        } else if (coffeeItem.status == "1") {
            coffeeItem.status = ""
            favDB.removeFav(coffeeItem.id)
            favBtn.setImageResource(R.drawable.heart)
            favBtn.isSelected = false

            upvotesRefLike.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    try {
                        val currentValue = mutableData.getValue(Int::class.java)
                        if (currentValue == null) {
                            mutableData.value = 1
                        } else {
                            mutableData.value = currentValue - 1
                            Handler(Looper.getMainLooper()).post {
                                textLike.text = mutableData.value.toString()
                            }
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

    private class DiffCallback : DiffUtil.ItemCallback<RealEstate>() {
        override fun areItemsTheSame(oldItem: RealEstate, newItem: RealEstate): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RealEstate, newItem: RealEstate): Boolean {
            return oldItem == newItem
        }
    }
}