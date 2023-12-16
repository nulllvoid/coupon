package com.nullvoid.coupon.api.coupon.repository.couponConfiguration

import com.nullvoid.coupon.api.coupon.entity.CouponConfiguration
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

interface CouponConfigMapper {

    @Insert("insert into couponConfiguration(couponCode,globalTotalRepeatCount,userTotalRepeatCount,userDailyRepeatCount,userWeeklyRepeatCount) values(#{couponCode}, #{globalTotalRepeatCount}, #{userTotalRepeatCount}, #{userDailyRepeatCount}, #{userWeeklyRepeatCount})")
    @Options(keyProperty = "couponCode")
    fun save(couponConfiguration: CouponConfiguration)

    @Select("select * from couponConfiguration where couponCode=#{couponCode}")
    fun findByCouponCode(couponCode: String): CouponConfiguration?

//        @Select
//        ("""
//        SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END
//        FROM coupon_configurations
//        WHERE couponCode = #{couponCode};
//        """)
//        suspend fun isCouponCodeConfigured(couponCode: String): Boolean
}
