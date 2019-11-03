package com.example.swell.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.swell.domain.Spot
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit


@BindingAdapter("spotName")
fun TextView.setSpotName(item: Spot?) {
    item?.let {
        text = ConvertSpot.idToName(item.magicSeaWeedSpotId)
    }
}

@BindingAdapter("date")
fun TextView.setDate(item: Spot?) {
    item?.let {
        var date: String
        val duration: Long = LocalDateTime.now().until(item.localDateTime, ChronoUnit.DAYS)

        when (duration) {
            0L -> date = "Today"
            1L -> date = "Tomorrow"
            -1L -> date = "Yesterday"
            else -> {
                val formatters: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                date = item.localDateTime!!.format(formatters)
            }
        }
        text = date
    }
}

@BindingAdapter("time")
fun TextView.setTime(item: Spot?) {
    item?.let {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val formatted = formatter.format(item.localDateTime)
        text = formatted
    }
}

@BindingAdapter("period")
fun TextView.setPeriod(item: Spot?) {
    item?.let {
        val periods = ArrayList<Double?>()
        periods.add(item.swell?.components?.combined?.period)
        periods.add(item.swell?.components?.primary?.period)
        periods.add(item.swell?.components?.secondary?.period)
        periods.add(item.swell?.components?.tertiary?.period)
        var min = Double.MAX_VALUE
        var max = Double.MIN_VALUE
        for (value in periods) {
            val double = value ?: -1.00
            if (double < min && double >= 0) {
                min = double
            }
            if (double > max) {
                max = double
            }
        }
        text = String.format("%s s - %s s", min, max)
    }
}

@BindingAdapter("minHeight")
fun TextView.setMinHeight(item: Spot?) {
    item?.let {
        val heights = ArrayList<Double?>()
        heights.add(item.swell?.components?.combined?.height)
        heights.add(item.swell?.components?.primary?.height)
        heights.add(item.swell?.components?.secondary?.height)
        heights.add(item.swell?.components?.tertiary?.height)
        var min = Double.MAX_VALUE
        for (value in heights) {
            val double = value ?: Double.MAX_VALUE
            if (double < min) {
                min = double
            }
        }
        text = String.format("%s m", min)
    }
}

@BindingAdapter("maxHeight")
fun TextView.setMaxHeight(item: Spot?) {
    item?.let {
        val heights = ArrayList<Double?>()
        heights.add(item.swell?.components?.combined?.height)
        heights.add(item.swell?.components?.primary?.height)
        heights.add(item.swell?.components?.secondary?.height)
        heights.add(item.swell?.components?.tertiary?.height)
        var max = Double.MIN_VALUE
        for (value in heights) {
            val double = value ?: Double.MIN_VALUE
            if (double > max) {
                max = double
            }
        }
        text = String.format("%s m", max)
    }
}

@BindingAdapter("fullDate")
fun TextView.setFullDate(item: Spot?) {
    item?.let {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val formatted = formatter.format(item.localDateTime)
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