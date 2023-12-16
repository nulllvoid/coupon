package com.nullvoid.coupon.api.coupon.service.interfaces

import com.nullvoid.coupon.model.enums.Validity
import com.nullvoid.coupon.model.request.CreateConfigurationRequest

interface CouponService {
    suspend fun createConfiguration(request: CreateConfigurationRequest): String

    suspend fun isValid(couponCode: String, userId: String): Validity

    suspend fun applyCoupon(couponCode: String, userId: String)
}
