package com.nullvoid.coupon.model

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Configuration(
    val globalTotalRepeatCount: Int,
    val userTotalRepeatCount: Int,
    val userDailyRepeatCount: Int,
    val userWeeklyRepeatCount: Int
)
