package com.zalesskyi.redditclient.data.base

import com.zalesskyi.redditclient.data.network.exceptions.NoNetworkException
import com.zalesskyi.redditclient.data.network.exceptions.ServerException
import io.reactivex.Single
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkErrorUtils {

    private const val SERVER_ERROR_CODE = 500
    private const val SERVER_ERROR_CODE_1 = 502

    fun <T> rxParseSingleError() = Function<Throwable, Single<T>> {
        Single.error<T>(parseError(it))
    }

    private fun parseError(throwable: Throwable): Throwable? =
        if (throwable is HttpException) {
            // return this exception in case of error with 500 code
            when (throwable.code()) {
                SERVER_ERROR_CODE, SERVER_ERROR_CODE_1 -> ServerException().initCause(throwable)
                else -> RuntimeException()
            }
        } else when {
            isConnectionProblem(throwable) -> NoNetworkException()
            isServerConnectionProblem(throwable) -> ServerException()
            else -> throwable
        }

    private fun isServerConnectionProblem(throwable: Throwable): Boolean =
        throwable is SocketException || throwable is SocketTimeoutException

    private fun isConnectionProblem(throwable: Throwable): Boolean =
        throwable is UnknownHostException || throwable is ConnectException
}
