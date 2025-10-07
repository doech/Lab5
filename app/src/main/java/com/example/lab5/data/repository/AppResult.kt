package com.example.lab5.data.repository

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

sealed class AppResult<out T> {
    data class Success<T>(val value: T) : AppResult<T>()
    data class Error(val kind: NetworkError, val message: String? = null) : AppResult<Nothing>()
}

sealed class NetworkError {
    object Network : NetworkError()      // sin conexión / IO
    object Timeout : NetworkError()
    object NotFound : NetworkError()     // 404
    object Server : NetworkError()       // 5xx
    object Unknown : NetworkError()

    companion object {
        fun from(t: Throwable): NetworkError = when (t) {
            is SocketTimeoutException -> Timeout
            is IOException -> Network
            is HttpException -> when (t.code()) {
                404 -> NotFound
                in 500..599 -> Server
                else -> Unknown
            }
            else -> Unknown
        }
    }
}
