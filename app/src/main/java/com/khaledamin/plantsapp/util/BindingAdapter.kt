package com.khaledamin.plantsapp.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.khaledamin.plantsapp.R

@BindingAdapter("imgUrl")
fun convertUrlToImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).placeholder(R.drawable.ic_plant_placeholder)
        .into(imageView)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("text")
fun setText(textView: TextView, text: String?) = when (text) {
    "", null -> {
        textView.text = "NA"
    }
    else -> {
        textView.text = text
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("year")
fun setYear(textView: TextView, year: Int?) = when (year) {
    null, 0 -> {
        textView.text = "NA"
    }
    else -> {
        textView.text = year.toString()
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("status")
fun setStatus(textView: TextView, text: String?) = when (text) {
    null, "" -> {
        textView.text = "NA"
    }
    else -> {
        textView.text = "IPNI $text"
    }
}