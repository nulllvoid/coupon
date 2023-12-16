package com.nullvoid.coupon.api.coupon.repository.userCouponUsage

import com.nullvoid.coupon.api.coupon.entity.UserCouponUsage
import jakarta.inject.Singleton

@Singleton
open class UserCouponUsageRepoImpl(
    private val userCouponUsageMapper: UserCouponUsageMapper
) : UserCouponUsageRepository {
    override fun save(userCouponUsage: UserCouponUsage): UserCouponUsage {
        userCouponUsageMapper.save(userCouponUsage)
        return userCouponUsage
    }

    override fun doesUserExists(userId: String): Boolean {
        return userCouponUsageMapper.doesUserExists(userId)
    }

    override fun getUserTotalUsageCount(couponCode: String, userId: String): Int {
        return userCouponUsageMapper.getUserTotalUsageCount(couponCode, userId)
    }

    override fun getUserDailyUsage(couponCode: String, userId: String): Int {
        return userCouponUsageMapper.getUserDailyUsage(couponCode, userId)
    }

    override fun getUserWeeklyUsage(couponCode: String, userId: String): Int {
        return userCouponUsageMapper.getUserWeeklyUsage(couponCode, userId)
    }

    override fun getGlobalUsageCount(couponCode: String): Int {
        return userCouponUsageMapper.getGlobalUsageCount(couponCode)
    }
}
