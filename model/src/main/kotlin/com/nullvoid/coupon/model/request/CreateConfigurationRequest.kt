package com.nullvoid.coupon.model.request

import com.nullvoid.coupon.model.Configuration
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateConfigurationRequest(
    val couponCode: String,
    val configuration: Configuration
)
