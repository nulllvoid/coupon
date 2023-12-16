package com.nullvoid.coupon.model

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
data class Configuration(
    @field:NonNull
    val globalTotalRepeatCount: Int = 0,

    @field:NonNull
    val userTotalRepeatCount: Int = 0,

    @field:NonNull
    val userDailyRepeatCount: Int = 0,

    @field:NonNull
    val userWeeklyRepeatCount: Int = 0
)
