package com.example.swell.util

object ConvertSpot {
    fun idToName(id: Long?): String {
        when (id) {
            194L -> return "Nazare"
            4048L -> return "De Panne"
            else -> return id.toString()
        }
    }

    fun nameToId(name: String?): Long {
        when (name) {
            "Nazare" -> return 194L
            "De Panne" -> return 4048L
            else -> return 0L
        }
    }
}