package com.murali.telstraassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.murali.telstraassignment.R

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

@BindingAdapter("imagePlaceHolder")
fun loadImage(imageView: ImageView, url: String?){
    Glide.with(imageView.context).load(url).placeholder(R.drawable.ic_launcher_background).into(imageView)
}