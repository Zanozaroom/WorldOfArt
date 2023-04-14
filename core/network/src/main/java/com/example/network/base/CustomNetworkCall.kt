package com.example.network.base

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


class CustomNetworkCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
): Call<CustomNetworkResponse<S, E>> {
    /**
     * Create a new, identical call to this one which can be enqueued or executed even if this call
     * has already been.
     */
    override fun clone(): Call<CustomNetworkResponse<S, E>> = CustomNetworkCall(delegate.clone(), errorConverter)

    /**
     * Synchronously send the request and return its response.
     *
     * @throws IOException if a problem occurred talking to the server.
     * @throws RuntimeException (and subclasses) if an unexpected error occurs creating the request or
     * decoding the response.
     */
    override fun execute(): Response<CustomNetworkResponse<S, E>> {
        throw UnsupportedOperationException("Custom NetworkResponseCall doesn't support execute")
    }

    /**
     * Asynchronously send the request and notify `callback` of its response or if an error
     * occurred talking to the server, creating the request, or processing the response.
     */
    override fun enqueue(callback: Callback<CustomNetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@CustomNetworkCall,
                            Response.success(Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@CustomNetworkCall,
                            Response.success(SuccessEmpty)
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@CustomNetworkCall,
                            Response.success(ApiError(errorBody, code))
                        )
                    } else {
                        callback.onResponse(
                            this@CustomNetworkCall,
                            Response.success(ApiError(null,code))
                        )
                    }
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkError(throwable)
                    else -> UnknownError(throwable)
                }
                callback.onResponse(this@CustomNetworkCall, Response.success(networkResponse))
            }
        })
            }
    /**
     * Returns true if this call has been either [executed][.execute]
     * or [ ][.enqueue]. It is an error to execute or enqueue a call more than once.
     */
    override fun isExecuted(): Boolean  = delegate.isExecuted

    /**
     * Cancel this call. An attempt will be made to cancel in-flight calls, and if the call has not
     * yet been executed it never will be.
     */
    override fun cancel() = delegate.cancel()

    /** True if [.cancel] was called.  */
    override fun isCanceled(): Boolean = delegate.isCanceled

    /** The original HTTP request.  */
    override fun request(): Request = delegate.request()

    /**
     * Returns a timeout that spans the entire call: resolving DNS, connecting, writing the request
     * body, server processing, and reading the response body. If the call requires redirects or
     * retries all must complete within one timeout period.
     */
    override fun timeout(): Timeout = delegate.timeout()
}