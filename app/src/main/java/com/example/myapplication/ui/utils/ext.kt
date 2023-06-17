package com.example.myapplication.ui.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


fun showToast(context: Context, message: Any) {
    Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT).show()
}
fun ImageView.loadImage(image : String){
    Glide.with(this).load(image).into(this)
}