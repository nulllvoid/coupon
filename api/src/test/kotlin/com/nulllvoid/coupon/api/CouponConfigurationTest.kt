
package com.nulllvoid.coupon.api

import com.nullvoid.coupon.api.coupon.entity.CouponConfiguration
import com.nullvoid.coupon.api.coupon.repository.couponConfiguration.CouponConfigRepository
import com.nullvoid.coupon.model.Configuration
import com.nullvoid.coupon.model.request.CreateConfigurationRequest
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
@MicronautTest(transactional = false)
class CouponConfigurationTest(
    private val couponConfigRepository: CouponConfigRepository
) {

    @Inject
    @field:Client("/coupon")
    lateinit var client: HttpClient
    @BeforeEach
    fun setUp() = runTest {
    }

    @AfterEach
    fun tearDown() = runTest {
    }

    @Test
    fun canAddConfiguration() = runTest {
        val createConfigurationRequest = CreateConfigurationRequest(
            couponCode = "test",
            configuration = Configuration(
                userDailyRepeatCount = 1,
                userWeeklyRepeatCount = 1,
                userTotalRepeatCount = 2,
                globalTotalRepeatCount = 10000
            )
        )
        val endpoint = "/configuration"
        val request = HttpRequest.POST(
            endpoint,
            createConfigurationRequest
        )

        // Non-existing item should be created
        val response: HttpResponse<Any> = withContext(Dispatchers.IO) {
            client.toBlocking().exchange(request)
        }
        val expectedResponse = CouponConfiguration(
            couponCode = createConfigurationRequest.couponCode,
            userTotalRepeatCount = createConfigurationRequest.configuration.userTotalRepeatCount,
            userWeeklyRepeatCount = createConfigurationRequest.configuration.userWeeklyRepeatCount,
            userDailyRepeatCount = createConfigurationRequest.configuration.userDailyRepeatCount,
            globalTotalRepeatCount = createConfigurationRequest.configuration.globalTotalRepeatCount
        )
        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertEquals(couponConfigRepository.findByCouponCode(createConfigurationRequest.couponCode), expectedResponse)
    }
}
