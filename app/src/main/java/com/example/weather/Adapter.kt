package com.example.weather

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("imgUrl")
fun bindImg(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUrl = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_sun)
                    .error(R.drawable.ic_sun)
            )
            .into(imgView)
    }

}



@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("textFormat")
fun textFormat(text: TextView, string: String?){

  /*  val date = LocalDate.parse(string, DateTimeFormatter.ISO_DATE)
    val c = Calendar.getInstance()
    c.time = date // yourdate is an object of type Date
    val dayOfWeek = c[Calendar.DAY_OF_WEEK] // this will for example return 3 for tuesday
    val dayWeekText: String = SimpleDateFormat("EEEE").format(date)*/

    /*val date = Date()
    val c = Calendar.getInstance()
    c.time = date
    val dayOfWeek = c[Calendar.DAY_OF_WEEK]
    println("Day of week in number:$dayOfWeek")
    val dayWeekText = SimpleDateFormat("EEEE").format(date)
    println("Day of week in text:$dayWeekText")
*/

}




