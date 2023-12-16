package com.nullvoid.coupon.model.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ApplyCouponCodeRequest(
    val couponCode: String,
    val userId: String
)
