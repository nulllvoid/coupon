package com.nullvoid.coupon.api.utils.exception

import com.nullvoid.coupon.api.utils.ErrorResponse
import com.nullvoid.coupon.api.utils.logger
import io.micronaut.context.env.Environment
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Error
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@Singleton
class CouponExceptionHandler(private var environment: Environment) : ExceptionHandler<Exception, HttpResponse<ErrorResponse>> {
    private val logger = logger()

    @Error(global = true, exception = Exception::class)
    override fun handle(request: HttpRequest<*>?, exception: Exception?): HttpResponse<ErrorResponse> {

        logger.error(request.toString(), exception)
        val errorMessage: ErrorResponse

        when (exception) {
            is CouponException -> {
                errorMessage = exception.error.let {
                    ErrorResponse(
                        it.code,
                        it.getMessage(exception.context),
                        it.httpStatus
                    )
                }
            }
            is ConstraintViolationException, is ValidationException -> {
                errorMessage = ErrorResponse(
                    CouponError.ERR_1003.code,
                    exception.message,
                    HttpStatus.BAD_REQUEST
                )
            }
            else -> {
                errorMessage = ErrorResponse(
                    CouponError.ERR_1003.code,
                    exception?.message,
                    HttpStatus.BAD_REQUEST
                )
            }
        }
        return getResponse(errorMessage.httpStatus, errorMessage)
    }

    /**
     * Generate HTTPErrorResponse based on HttpStatus and ErrorResponse
     * @param HttpStatus, ErrorResponse
     * @return HttpResponse<ErrorResponse>
     */
    private fun getResponse(httpStatus: HttpStatus?, errorMessage: ErrorResponse): HttpResponse<ErrorResponse> {
        return when {
            httpStatus?.equals(HttpStatus.BAD_REQUEST) == true -> HttpResponse.badRequest(errorMessage)
            httpStatus?.equals(HttpStatus.SERVICE_UNAVAILABLE) == true -> HttpResponse.serverError(errorMessage)
            httpStatus?.equals(HttpStatus.INTERNAL_SERVER_ERROR) == true -> HttpResponse.serverError(errorMessage)
            httpStatus?.equals(HttpStatus.NOT_FOUND) == true -> HttpResponse.notFound(errorMessage)
            httpStatus?.equals(HttpStatus.UNAUTHORIZED) == true -> HttpResponse.status(HttpStatus.FORBIDDEN)
            else -> HttpResponse.serverError(errorMessage)
        }
    }

    @Error(status = HttpStatus.NOT_FOUND, global = true)
    fun notFound(@Suppress("UNUSED_PARAMETER") request: HttpRequest<*>?): HttpResponse<ErrorResponse> {
        return HttpResponse.notFound(
            ErrorResponse(
                CouponError.ERR_1001.code,
                CouponError.ERR_1001.message,
                HttpStatus.NOT_FOUND
            )
        )
    }
}
