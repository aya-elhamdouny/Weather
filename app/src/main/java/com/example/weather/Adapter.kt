package com.example.weather

import android.widget.ImageView
import android.widget.TextView
import android.widget.WrapperListAdapter
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.model.weather

@BindingAdapter("imgUrl")
fun bindImg(imgView : ImageView , imgUrl :String?){
    imgUrl?.let {
        val imgUrl = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_sun)
                .error(R.drawable.ic_sun))
            .into(imgView)
    }

}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<weather>?) {
    val adapter = recyclerView.adapter as WeatherAdapter
    adapter.submitList(data)
}


