package com.nullvoid.coupon.api.utils.exception

class CouponException(var error: CouponError, var context: String) : RuntimeException()
