
package com.nulllvoid.coupon.api

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
import java.util.UUID

@OptIn(ExperimentalCoroutinesApi::class)
@MicronautTest(transactional = false)
class CouponConfigurationTest {

    @Inject
    @field:Client("/config")
    lateinit var client: HttpClient
    @BeforeEach
    fun setUp() = runTest {
    }

    @AfterEach
    fun tearDown() = runTest {
    }

    @Test
    fun canCreateNewItems() = runTest {
        val endpoint = "/item"
        val creator = UUID.randomUUID()
        val request = HttpRequest.POST(
            endpoint,

            ""
        )

        // Non-existing item should be created
        val response: HttpResponse<Any> = withContext(Dispatchers.IO) {
            client.toBlocking().exchange(request)
        }
        Assertions.assertEquals(HttpStatus.CREATED, response.status)
    }
}
