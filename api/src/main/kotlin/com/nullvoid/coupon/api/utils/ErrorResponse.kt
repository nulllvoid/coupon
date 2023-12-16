package com.nullvoid.coupon.api.utils

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.http.HttpStatus
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ErrorResponse(
    @JsonProperty("errorCode")
    var errorCode: String = "",
    @JsonProperty("errorMessage")
    var errorMessage: String? = "",
    @JsonProperty("httpStatus")
    var httpStatus: HttpStatus?,
)
