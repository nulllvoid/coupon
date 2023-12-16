package com.nullvoid.coupon.api.utils.exception

import io.micronaut.http.HttpStatus

enum class CouponError(
    var code: String,
    var message: String,
    var httpStatus: HttpStatus
) {
    ERR_1000("ERR_1000", "Invalid Coupon", HttpStatus.NOT_ACCEPTABLE),
    ERR_1001("ERR_1001", "Service Unavailable. Please contact admin", HttpStatus.SERVICE_UNAVAILABLE),
    ERR_1002("ERR_1002", "Coupon has Expired", HttpStatus.NOT_ACCEPTABLE),
    ERR_1003("ERR_1003", "Invalid Input", HttpStatus.BAD_REQUEST),
    ERR_1004("ERR_1004", "Not Found", HttpStatus.NOT_FOUND);
    fun getMessage(param: String): String {
        var m1 = message
        if (param.isNotEmpty()) {
            m1 += param
        }
        return m1
    }
    override fun toString(): String {
        return "$code: $message"
    }
}
