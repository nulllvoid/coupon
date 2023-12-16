package com.nullvoid.coupon.api.coupon.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity
import java.util.Date

@Introspected
@MappedEntity
data class UserCouponUsage(
    var id: Long? = null,
    val userId: String,
    val couponCode: String,
    val usageDate: Date
)
