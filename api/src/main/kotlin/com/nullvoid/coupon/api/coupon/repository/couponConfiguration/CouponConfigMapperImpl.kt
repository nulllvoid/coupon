package com.nullvoid.coupon.api.coupon.repository.couponConfiguration

import com.nullvoid.coupon.api.coupon.entity.CouponConfiguration
import jakarta.inject.Singleton
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import javax.transaction.Transactional

@Singleton
open class CouponConfigMapperImpl(
    private val sqlSessionFactory: SqlSessionFactory
) : CouponConfigMapper {
    @Transactional
    override fun save(couponConfiguration: CouponConfiguration) {
        sqlSessionFactory.openSession().use { sqlSession ->
            getCouponConfigurationRepo(sqlSession).save(couponConfiguration)
            sqlSession.commit()
        }
    }
    @Transactional
    override fun findByCouponCode(couponCode: String): CouponConfiguration? {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getCouponConfigurationRepo(sqlSession).findByCouponCode(couponCode)
        }
    }

    private fun getCouponConfigurationRepo(sqlSession: SqlSession): CouponConfigMapper {
        return sqlSession.getMapper(CouponConfigMapper::class.java)
    }
}
