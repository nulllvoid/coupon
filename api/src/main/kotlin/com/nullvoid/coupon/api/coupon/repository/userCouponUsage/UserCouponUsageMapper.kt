package com.nullvoid.coupon.api.coupon.repository.userCouponUsage

import com.nullvoid.coupon.api.coupon.entity.UserCouponUsage
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

interface UserCouponUsageMapper {

    @Insert("insert into UserCouponUsage(userId, couponCode, usageDate) values(#{userId}, #{couponCode}, #{usageDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun save(userCouponUsage: UserCouponUsage)

    @Select(
        """
        SELECT COUNT(*)
        FROM UserCouponUsage
        WHERE userId = #{userId}
          AND couponCode = #{couponCode};
    """
    )
    fun getUserTotalUsageCount(couponCode: String, userId: String): Int

    @Select(
        """
        SELECT COUNT(*)
        FROM UserCouponUsage
        WHERE user_id = #{userId}
          AND coupon_code = #{couponCode}
          AND DATE(usage_fate) = CURDATE();
    """
    )
    fun getUserDailyUsage(couponCode: String, userId: String): Int

    @Select(
        """
        SELECT COUNT(*) as userWeeklyUsage
        FROM UserCouponUsage
        WHERE user_id = #{userId}
          AND coupon_code =#{couponCode}
          AND usageDate >= DATE_SUB(CURDATE(), INTERVAL DAYOFWEEK(CURDATE()) - 1 DAY)
          AND usageDate < DATE_ADD(DATE_SUB(CURDATE(), INTERVAL DAYOFWEEK(CURDATE()) - 1 DAY), INTERVAL 7 DAY);
        """
    )
    fun getUserWeeklyUsage(couponCode: String, userId: String): Int

    @Select(
        """
        SELECT COUNT(*)
        FROM UserCouponUsage
        WHERE couponCode = #{couponCode};
    """
    )
    fun getGlobalUsageCount(couponCode: String): Int

    @Select(
        """
        SELECT EXISTS (
        SELECT 1
        FROM UserCouponUsage
        WHERE userId = #{userId}
    )
        """
    )
    fun doesUserExists(userId: String): Boolean
}
