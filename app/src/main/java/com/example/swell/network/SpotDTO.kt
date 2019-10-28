package com.example.swell.network

import android.os.Parcelable
import com.example.swell.database.*
import com.example.swell.domain.*
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkSpotContainer(val spots: List<NetworkSpot>) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkSpot(
    val timestamp: Long?,
    val localTimestamp: Long?,
    val issueTimestamp: Long?,
    val fadedRating: Double?,
    val solidRating: Double?,
    val swell: NetworkSwell?,
    val wind: NetworkWind?,
    val condition: NetworkCondition?,
    val charts: NetworkChart?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkSwell(
    val minBreakingHeight: Double?,
    val absMinBreakingHeight: Double?,
    val maxBreakingHeight: Double?,
    val absMaxBreakingHeight: Double?,
    val unit: String?,
    val components: NetworkComponent?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkComponent(
    val combined: NetworkCombined?,
    val primary: NetworkPrimary?,
    val secondary: NetworkSecondary?,
    val tertiary: NetworkTertiary?

) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkCombined(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkPrimary(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkSecondary(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkTertiary(
    val height: Double?,
    val period: Double?,
    val direction: Double?,
    val compassDirection: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkWind(
    val speed: Double?,
    val direction: Double?,
    val compassDirection: String?,
    val chill: Double?,
    val gusts: Double?,
    val unit: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkCondition(
    val pressure: Double?,
    val temperature: Double?,
    val unitPressure: String?,
    val unit: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkChart(
    val swell: String?,
    val period: String?,
    val wind: String?,
    val pressure: String?,
    val sst: String?
) : Parcelable

fun List<NetworkSpot>.asDatabaseModel(magicSeaWeedSpotId: Long): Array<DatabaseSpot> {
    return map {
        DatabaseSpot(
            magicSeaWeedSpotId = magicSeaWeedSpotId,
            spotId = 0L,
            timestamp = it.timestamp,
            localTimestamp = it.localTimestamp,
            issueTimestamp = it.issueTimestamp,
            fadedRating = it.fadedRating,
            solidRating = it.solidRating,
            swell = DatabaseSwell(
                minBreakingHeight = it.swell?.minBreakingHeight,
                absMinBreakingHeight = it.swell?.absMinBreakingHeight,
                maxBreakingHeight = it.swell?.maxBreakingHeight,
                absMaxBreakingHeight = it.swell?.absMaxBreakingHeight,
                swell_unit = it.swell?.unit,
                components = DatabaseComponent(
                    combined = DatabaseCombined(
                        comb_height = it.swell?.components?.combined?.height,
                        comb_period = it.swell?.components?.combined?.period,
                        comb_direction = it.swell?.components?.combined?.direction,
                        comb_compassDirection = it.swell?.components?.combined?.compassDirection
                    ),
                    primary = DatabasePrimary(
                        prim_height = it.swell?.components?.primary?.height,
                        prim_period = it.swell?.components?.primary?.period,
                        prim_direction = it.swell?.components?.primary?.direction,
                        prim_compassDirection = it.swell?.components?.primary?.compassDirection
                    ),
                    secondary = DatabaseSecondary(
                        sec_height = it.swell?.components?.secondary?.height,
                        sec_period = it.swell?.components?.secondary?.period,
                        sec_direction = it.swell?.components?.secondary?.direction,
                        sec_compassDirection = it.swell?.components?.secondary?.compassDirection
                    ),
                    tertiary = DatabaseTertiary(
                        tert_height = it.swell?.components?.tertiary?.height,
                        tert_period = it.swell?.components?.tertiary?.period,
                        tert_direction = it.swell?.components?.tertiary?.direction,
                        tert_compassDirection = it.swell?.components?.tertiary?.compassDirection
                    )
                )

            ),
            wind = DatabaseWind(
                wind_speed = it.wind?.speed,
                wind_direction = it.wind?.direction,
                wind_compassDirection = it.wind?.compassDirection,
                wind_chill = it.wind?.chill,
                wind_gusts = it.wind?.gusts,
                wind_unit = it.wind?.unit
            ),
            condition = DatabaseCondition(
                cond_pressure = it.condition?.pressure,
                cond_temperature = it.condition?.temperature,
                cond_unitPressure = it.condition?.unitPressure,
                cond_unit = it.condition?.unit
            ),
            charts = DatabaseChart(
                chart_swell = it.charts?.swell,
                chart_period = it.charts?.period,
                chart_wind = it.charts?.wind,
                chart_pressure = it.charts?.pressure,
                chart_sst = it.charts?.sst
            )
        )
    }.toTypedArray()
}

fun List<NetworkSpot>.asDomainModel(): List<Spot> {
    return map {
        Spot(
            spotId = 0L,
            magicSeaWeedSpotId = null,
            timestamp = Network.getDate(it.timestamp),
            localTimestamp = Network.getDate(it.localTimestamp),
            issueTimestamp = Network.getDate(it.issueTimestamp),
            fadedRating = it.fadedRating,
            solidRating = it.solidRating,
            swell = Swell(
                it.swell?.minBreakingHeight,
                it.swell?.absMinBreakingHeight,
                it.swell?.maxBreakingHeight,
                it.swell?.absMaxBreakingHeight,
                it.swell?.unit,
                Component(
                    Combined(
                        it.swell?.components?.combined?.height,
                        it.swell?.components?.combined?.period,
                        it.swell?.components?.combined?.direction,
                        it.swell?.components?.combined?.compassDirection
                    ),
                    Primary(
                        it.swell?.components?.primary?.height,
                        it.swell?.components?.primary?.period,
                        it.swell?.components?.primary?.direction,
                        it.swell?.components?.primary?.compassDirection
                    ),
                    Secondary(
                        it.swell?.components?.secondary?.height,
                        it.swell?.components?.secondary?.period,
                        it.swell?.components?.secondary?.direction,
                        it.swell?.components?.secondary?.compassDirection
                    ),
                    Tertiary(
                        it.swell?.components?.tertiary?.height,
                        it.swell?.components?.tertiary?.period,
                        it.swell?.components?.tertiary?.direction,
                        it.swell?.components?.tertiary?.compassDirection
                    )
                )

            ),
            wind = Wind(
                it.wind?.speed,
                it.wind?.direction,
                it.wind?.compassDirection,
                it.wind?.chill,
                it.wind?.gusts,
                it.wind?.unit
            ),
            condition = Condition(
                it.condition?.pressure,
                it.condition?.temperature,
                it.condition?.unitPressure,
                it.condition?.unit
            ),
            charts = Chart(
                it.charts?.swell,
                it.charts?.period,
                it.charts?.wind,
                it.charts?.pressure,
                it.charts?.sst
            )
        )
    }
}
