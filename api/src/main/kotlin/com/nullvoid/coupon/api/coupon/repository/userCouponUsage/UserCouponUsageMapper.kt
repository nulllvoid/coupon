package com.nullvoid.coupon.api.coupon.repository.userCouponUsage

import com.nullvoid.coupon.api.coupon.entity.UserCouponUsage
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

interface UserCouponUsageMapper {

    @Insert("INSERT INTO UserCouponUsage(userId, couponCode, usageDate) VALUES (#{userId}, #{couponCode}, CURRENT_DATE)")
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
    SELECT COUNT(*) as userWeeklyUsage
    FROM UserCouponUsage
    WHERE userId = #{userId}
  AND couponCode = #{couponCode}
  AND usageDate >= DATEADD('DAY', - (DAYOFWEEK(CURDATE()) - 1), CURDATE())
  AND usageDate < DATEADD('DAY', 7, DATEADD('DAY', - (DAYOFWEEK(CURDATE()) - 1), CURDATE()));
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
    SELECT COUNT(*) as userDailyUsage
    FROM UserCouponUsage
    WHERE userId = #{userId}
      AND couponCode = #{couponCode}
      AND usageDate = CURRENT_DATE
    """
    )
    fun getUserDailyUsage(couponCode: String, userId: String): Int

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
