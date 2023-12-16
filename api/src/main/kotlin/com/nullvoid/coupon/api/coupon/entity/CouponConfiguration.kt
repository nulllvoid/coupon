package com.nullvoid.coupon.api.coupon.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity

@Introspected
@MappedEntity
data class CouponConfiguration(
    val couponCode: String,
    val globalTotalRepeatCount: Int,
    val userTotalRepeatCount: Int,
    val userDailyRepeatCount: Int,
    val userWeeklyRepeatCount: Int
)
