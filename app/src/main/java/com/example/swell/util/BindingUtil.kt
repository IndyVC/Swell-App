package com.example.swell.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.swell.domain.Spot
import java.text.SimpleDateFormat

@BindingAdapter("spotName")
fun TextView.setSpotName(item: Spot?) {
    item?.let {
        text = ConvertSpot.idToName(item.magicSeaWeedSpotId)
    }
}

@BindingAdapter("date")
fun TextView.setDate(item: Spot?) {
    item?.let {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val formatted = formatter.format(item.localTimestamp)
        text = formatted
    }
}

@BindingAdapter("time")
fun TextView.setTime(item: Spot?) {
    item?.let {
        val formatter = SimpleDateFormat("HH:mm")
        val formatted = formatter.format(item.localTimestamp)
        text = formatted
    }
}

@BindingAdapter("height")
fun TextView.setHeight(item: Spot?) {
    item?.let {
        text = String.format("%s m", item.swell?.components?.combined?.height.toString())
    }
}

@BindingAdapter("period")
fun TextView.setPeriod(item: Spot?) {
    item?.let {
        text = String.format("%s s", item.swell?.components?.combined?.period.toString())
    }
}

@BindingAdapter("minBreakingHeight")
fun TextView.setMinBreakingHeight(item: Spot?) {
    item?.let {
        text = String.format("%s m", item.swell?.absMinBreakingHeight.toString())
    }
}

@BindingAdapter("maxBreakingHeight")
fun TextView.setMaxBreakingHeight(item: Spot?) {
    item?.let {
        text = String.format("%s m", item.swell?.absMaxBreakingHeight.toString())
    }
}

@BindingAdapter("fullDate")
fun TextView.setFullDate(item: Spot?) {
    item?.let {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val formatted = formatter.format(item.localTimestamp)
        text = formatted
    }
}

@BindingAdapter("temperature")
fun TextView.setTemperature(item: Spot?) {
    item?.let {
        text = String.format("%s °C", item.condition?.temperature.toString())
    }
}

@BindingAdapter("windChill")
fun TextView.windChill(item: Spot?) {
    item?.let {
        text = String.format("%s °C", item.wind?.chill.toString())
    }
}