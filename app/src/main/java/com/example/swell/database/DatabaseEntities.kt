package com.example.swell.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swell.domain.*
import com.example.swell.network.Network


@Entity
data class DatabaseSpot(
    val magicSeaWeedSpotId: Long,
    @PrimaryKey(autoGenerate = true)
    val spotId: Long,
    val timestamp: Long?,
    val localTimestamp: Long?,
    val issueTimestamp: Long?,
    val fadedRating: Double?,
    val solidRating: Double?,
    @Embedded
    val swell: DatabaseSwell?,
    @Embedded
    val wind: DatabaseWind?,
    @Embedded
    val condition: DatabaseCondition?,
    @Embedded
    val charts: DatabaseChart?
)

data class DatabaseSwell(
    val minBreakingHeight: Double?,
    val absMinBreakingHeight: Double?,
    val maxBreakingHeight: Double?,
    val absMaxBreakingHeight: Double?,
    val swell_unit: String?,
    @Embedded
    val components: DatabaseComponent?
)


data class DatabaseComponent(
    @Embedded
    val combined: DatabaseCombined?,
    @Embedded
    val primary: DatabasePrimary?,
    @Embedded
    val secondary: DatabaseSecondary?,
    @Embedded
    val tertiary: DatabaseTertiary?

)


data class DatabaseCombined(
    val comb_height: Double?,
    val comb_period: Double?,
    val comb_direction: Double?,
    val comb_compassDirection: String?
)


data class DatabasePrimary(
    val prim_height: Double?,
    val prim_period: Double?,
    val prim_direction: Double?,
    val prim_compassDirection: String?
)

data class DatabaseSecondary(
    val sec_height: Double?,
    val sec_period: Double?,
    val sec_direction: Double?,
    val sec_compassDirection: String?
)


data class DatabaseTertiary(
    val tert_height: Double?,
    val tert_period: Double?,
    val tert_direction: Double?,
    val tert_compassDirection: String?
)


data class DatabaseWind(
    val wind_speed: Double?,
    val wind_direction: Double?,
    val wind_compassDirection: String?,
    val wind_chill: Double?,
    val wind_gusts: Double?,
    val wind_unit: String?
)


data class DatabaseCondition(
    val cond_pressure: Double?,
    val cond_temperature: Double?,
    val cond_unitPressure: String?,
    val cond_unit: String?
)


data class DatabaseChart(
    val chart_swell: String?,
    val chart_period: String?,
    val chart_wind: String?,
    val chart_pressure: String?,
    val chart_sst: String?
)

fun List<DatabaseSpot>.asDomainModel(): List<Spot> {
    return map {
        Spot(
            spotId = it.spotId,
            magicSeaWeedSpotId = it.magicSeaWeedSpotId,
            dateTime = Network.getLocalDate(it.timestamp),
            localDateTime = Network.getLocalDate(it.localTimestamp),
            issueDateTime = Network.getLocalDate(it.issueTimestamp),
            fadedRating = it.fadedRating,
            solidRating = it.solidRating,
            swell = Swell(
                it.swell?.minBreakingHeight,
                it.swell?.absMinBreakingHeight,
                it.swell?.maxBreakingHeight,
                it.swell?.absMaxBreakingHeight,
                it.swell?.swell_unit,
                Component(
                    Combined(
                        it.swell?.components?.combined?.comb_height,
                        it.swell?.components?.combined?.comb_period,
                        it.swell?.components?.combined?.comb_direction,
                        it.swell?.components?.combined?.comb_compassDirection
                    ),
                    Primary(
                        it.swell?.components?.primary?.prim_height,
                        it.swell?.components?.primary?.prim_period,
                        it.swell?.components?.primary?.prim_direction,
                        it.swell?.components?.primary?.prim_compassDirection
                    ),
                    Secondary(
                        it.swell?.components?.secondary?.sec_height,
                        it.swell?.components?.secondary?.sec_period,
                        it.swell?.components?.secondary?.sec_direction,
                        it.swell?.components?.secondary?.sec_compassDirection
                    ),
                    Tertiary(
                        it.swell?.components?.tertiary?.tert_height,
                        it.swell?.components?.tertiary?.tert_period,
                        it.swell?.components?.tertiary?.tert_direction,
                        it.swell?.components?.tertiary?.tert_compassDirection
                    )
                )

            ),
            wind = Wind(
                it.wind?.wind_speed,
                it.wind?.wind_direction,
                it.wind?.wind_compassDirection,
                it.wind?.wind_chill,
                it.wind?.wind_gusts,
                it.wind?.wind_unit
            ),
            condition = Condition(
                it.condition?.cond_pressure,
                it.condition?.cond_temperature,
                it.condition?.cond_unitPressure,
                it.condition?.cond_unit
            ),
            charts = Chart(
                it.charts?.chart_swell,
                it.charts?.chart_period,
                it.charts?.chart_wind,
                it.charts?.chart_pressure,
                it.charts?.chart_sst
            )
        )
    }
}