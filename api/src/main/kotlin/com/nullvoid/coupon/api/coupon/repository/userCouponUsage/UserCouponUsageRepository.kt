package com.nullvoid.coupon.api.coupon.repository.userCouponUsage

import com.nullvoid.coupon.api.coupon.entity.UserCouponUsage

interface UserCouponUsageRepository {
    fun save(userCouponUsage: UserCouponUsage): UserCouponUsage?

    fun doesUserExists(userId: String): Boolean
    fun getUserTotalUsageCount(couponCode: String, userId: String): Int

    fun getUserDailyUsage(couponCode: String, userId: String): Int

    fun getUserWeeklyUsage(couponCode: String, userId: String): Int

    fun getGlobalUsageCount(couponCode: String): Int
}
