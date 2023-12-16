package com.nullvoid.coupon.api.coupon.repository.couponConfiguration

import com.nullvoid.coupon.api.coupon.entity.CouponConfiguration
import io.micronaut.core.annotation.NonNull
import jakarta.inject.Singleton

@Singleton
open class CouponConfigRepoImpl(
    private val couponConfigMapper: CouponConfigMapper
) : CouponConfigRepository {
    override fun findByCouponCode(couponCode: String): CouponConfiguration? {
        return couponConfigMapper.findByCouponCode(couponCode)
    }

    @NonNull
    override fun save(couponConfiguration: CouponConfiguration): CouponConfiguration {
        couponConfigMapper.save(couponConfiguration)
        return couponConfiguration
    }
}
