package com.nullvoid.coupon.api.coupon.controller

import com.nullvoid.coupon.api.coupon.repository.couponConfiguration.CouponConfigMapper
import com.nullvoid.coupon.api.coupon.service.interfaces.CouponService
import com.nullvoid.coupon.model.enums.Validity
import com.nullvoid.coupon.model.request.ApplyCouponCodeRequest
import com.nullvoid.coupon.model.request.CreateConfigurationRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import javax.validation.Valid

@Validated
@Controller
class CouponController(
    private var couponConfigMapper: CouponConfigMapper
) {

    @Inject
    lateinit var couponService: CouponService

    @Post("/configuration")
    suspend fun createConfiguration(@Body @Valid request: CreateConfigurationRequest): String {
        return couponService.createConfiguration(request)
    }
    @Get("/isValid")
    suspend fun isValid(
        @QueryValue("couponCode") couponCode: String,
        @QueryValue("userId") userId: String,
    ): Validity {
        return couponService.isValid(couponCode, userId)
    }

    @Post("/apply-coupon")
    suspend fun applyCoupon(@Body request: ApplyCouponCodeRequest): String {
        couponService.applyCoupon(couponCode = request.couponCode, userId = request.userId)
        return "CouponCode ${request.couponCode} applied Successfully"
    }
}
