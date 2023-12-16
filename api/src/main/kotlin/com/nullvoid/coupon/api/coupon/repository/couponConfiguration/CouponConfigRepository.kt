package com.nullvoid.coupon.api.coupon.repository.couponConfiguration

import com.nullvoid.coupon.api.coupon.entity.CouponConfiguration

interface CouponConfigRepository {

    fun findByCouponCode(couponCode: String): CouponConfiguration?
    fun save(couponConfiguration: CouponConfiguration): CouponConfiguration
}
