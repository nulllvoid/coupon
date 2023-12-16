package com.nullvoid.coupon.api.coupon.repository.userCouponUsage

import com.nullvoid.coupon.api.coupon.entity.UserCouponUsage
import jakarta.inject.Singleton
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import javax.transaction.Transactional

@Singleton
open class UserCouponUsageMapperImpl(
    private val sqlSessionFactory: SqlSessionFactory
) : UserCouponUsageMapper {
    @Transactional
    override fun save(userCouponUsage: UserCouponUsage) {
        sqlSessionFactory.openSession().use { sqlSession ->
            getUserCouponUsageRepo(sqlSession).save(userCouponUsage)
            sqlSession.commit()
        }
    }
    @Transactional
    override fun getUserTotalUsageCount(couponCode: String, userId: String): Int {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getUserCouponUsageRepo(sqlSession).getUserTotalUsageCount(couponCode, userId)
        }
    }

    @Transactional
    override fun getUserDailyUsage(couponCode: String, userId: String): Int {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getUserCouponUsageRepo(sqlSession).getUserDailyUsage(couponCode, userId)
        }
    }

    @Transactional
    override fun getUserWeeklyUsage(couponCode: String, userId: String): Int {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getUserCouponUsageRepo(sqlSession).getUserWeeklyUsage(couponCode, userId)
        }
    }

    @Transactional
    override fun getGlobalUsageCount(couponCode: String): Int {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getUserCouponUsageRepo(sqlSession).getGlobalUsageCount(couponCode)
        }
    }
    @Transactional
    override fun doesUserExists(userId: String): Boolean {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getUserCouponUsageRepo(sqlSession).doesUserExists(userId)
        }
    }

    private fun getUserCouponUsageRepo(sqlSession: SqlSession): UserCouponUsageMapper {
        return sqlSession.getMapper(UserCouponUsageMapper::class.java)
    }
}
