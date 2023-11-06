package com.example.imageview.data.repository

import android.content.res.Resources.NotFoundException
import android.util.Log
import retrofit2.Response

abstract class BaseRepository {

    protected suspend fun <T : Any> wrap(function: suspend () -> Response<T>): T {
        try {
            val response = function()
            if (response.isSuccessful) {
                return response.body() ?: throw NotFoundException()
            } else {
                handleErrorResponse(response)
            }
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun handleErrorResponse(response: Response<*>): Nothing {
        throw when (response.code()) {
            404 -> NotFoundException()
            else -> Exception("Repository failed with response: $response")
        }
    }

    private fun handleException(e: Exception): Nothing {
        Log.d("TAG", "Response failed: ${e.message}")
        throw NotFoundException()
    }
}
