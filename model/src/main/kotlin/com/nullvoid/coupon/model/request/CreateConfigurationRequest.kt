package com.nullvoid.coupon.model.request

import com.nullvoid.coupon.model.Configuration
import io.micronaut.serde.annotation.Serdeable
import javax.validation.Valid

@Serdeable
data class CreateConfigurationRequest(
    val couponCode: String,
    @field:Valid
    val configuration: Configuration
)
