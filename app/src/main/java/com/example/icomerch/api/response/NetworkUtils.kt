package com.example.icomerch.api.response

import com.google.gson.Gson
import retrofit2.Response

object NetworkUtils {

    fun handleApiError(response: Response<*>): ApiErrorResponse {
        val errorBody = response.errorBody()?.string()
        return if (!errorBody.isNullOrEmpty()) {
            try {
                Gson().fromJson(errorBody, ApiErrorResponse::class.java)
            } catch (e: Exception) {
                ApiErrorResponse(message = "Error parsing error response", httpStatus = "${response.code()}")
            }
        } else {
            ApiErrorResponse(message = "Unknown error", httpStatus = "${response.code()}")
        }
    }

}