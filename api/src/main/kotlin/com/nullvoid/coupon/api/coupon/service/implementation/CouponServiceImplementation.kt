package com.nullvoid.coupon.api.coupon.service.implementation

import com.nullvoid.coupon.api.coupon.entity.CouponConfiguration
import com.nullvoid.coupon.api.coupon.entity.UserCouponUsage
import com.nullvoid.coupon.api.coupon.repository.couponConfiguration.CouponConfigRepository
import com.nullvoid.coupon.api.coupon.repository.userCouponUsage.UserCouponUsageRepository
import com.nullvoid.coupon.api.coupon.service.interfaces.CouponService
import com.nullvoid.coupon.api.utils.exception.CouponError
import com.nullvoid.coupon.api.utils.exception.CouponException
import com.nullvoid.coupon.model.enums.Validity
import com.nullvoid.coupon.model.request.CreateConfigurationRequest
import jakarta.inject.Singleton
import java.util.Date

@Singleton
open class CouponServiceImplementation(
    private val couponConfigRepo: CouponConfigRepository,
    private val userCouponUsageRepository: UserCouponUsageRepository
) : CouponService {

    override suspend fun createConfiguration(request: CreateConfigurationRequest): String {
        val couponConfiguration = CouponConfiguration(
            couponCode = request.couponCode,
            globalTotalRepeatCount = request.configuration.globalTotalRepeatCount,
            userDailyRepeatCount = request.configuration.userDailyRepeatCount,
            userTotalRepeatCount = request.configuration.userTotalRepeatCount,
            userWeeklyRepeatCount = request.configuration.userWeeklyRepeatCount
        )
        couponConfigRepo.save(couponConfiguration)
        return "SuccessFully Added Configuration to ${request.couponCode}"
    }

    override suspend fun isValid(couponCode: String, userId: String): Validity {
        val configuration = couponConfigRepo.findByCouponCode(couponCode) ?: return Validity.INVALID
        val userExists = userCouponUsageRepository.doesUserExists(userId)

        if (!userExists) {
            return Validity.VALID
        } else {
            val userTotalRepeatCount = userCouponUsageRepository.getUserTotalUsageCount(couponCode, userId)
            val userDailyRepeatCount = userCouponUsageRepository.getUserDailyUsage(couponCode, userId)
            val userWeeklyRepeatCount = userCouponUsageRepository.getUserWeeklyUsage(couponCode, userId)
            val globalTotalRepeatCount = userCouponUsageRepository.getGlobalUsageCount(couponCode)
            if (userTotalRepeatCount > configuration.userTotalRepeatCount ||
                userDailyRepeatCount > configuration.userDailyRepeatCount ||
                userWeeklyRepeatCount > configuration.userWeeklyRepeatCount ||
                globalTotalRepeatCount > configuration.globalTotalRepeatCount
            ) {
                return Validity.EXPIRED
            }
        }
        return Validity.VALID
    }

    override suspend fun applyCoupon(couponCode: String, userId: String) {
        if (isValid(couponCode, userId) == Validity.VALID) {
            userCouponUsageRepository.save(
                UserCouponUsage(
                    couponCode = couponCode,
                    usageDate = Date(),
                    userId = userId,
                    id = null
                )
            )
        } else {
            throw CouponException(CouponError.ERR_1004, "Coupon Code $couponCode is ${Validity.INVALID.name}")
        }
    }
}
