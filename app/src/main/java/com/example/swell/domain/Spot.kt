package com.example.swell.domain

import java.util.*


data class Spot(
    val spotId: Long,
    val magicSeaWeedSpotId: Long?,
    val timestamp: Date?,
    val localTimestamp: Date?,
    val issueTimestamp: Date?,
    val fadedRating: Double?,
    val solidRating: Double?,
    val swell: Swell?,
    val wind: Wind?,
    val condition: Condition?,
    val charts: Chart?
)

data class Swell(
    val minBreakingHeight: Double?,
    val absMinBreakingHeight: Double?,
    val maxBreakingHeight: Double?,
    val absMaxBreakingHeight: Double?,
    val unit: String?,
    val components: Component?
)

data class Component(
    val combined: Combined?,
    val primary: Primary?,
    val secondary: Secondary?,
    val tertiary: Tertiary?

)

data class Combined(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
)

data class Primary(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
)


data class Secondary(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
)

data class Tertiary(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
)


data class Wind(
    val speed: Double?,
    val direction: Double?,
    val compassDirection: String?,
    val chill: Double?,
    val gusts: Double?,
    val unit: String?
)


data class Condition(
    val pressure: Double?,
    val temperature: Double?,
    val unitPressure: String?,
    val unit: String?
)


data class Chart(
    val swell: String?,
    val period: String?,
    val wind: String?,
    val pressure: String?,
    val sst: String?
)

