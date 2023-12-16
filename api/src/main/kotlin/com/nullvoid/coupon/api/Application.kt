package com.nullvoid.coupon.api

import com.nulllvoid.coupon.api.utils.annotations.Generated
import io.micronaut.runtime.Micronaut.build
import org.h2.tools.Server

@Generated
fun main(args: Array<String>) {
    Server.createWebServer().start()
    build()
        .args(*args)
        .packages("com.nulllvoid.coupon.api")
        .banner(false)
        .start()
}
