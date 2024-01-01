package com.example.icomerch.api.response

import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
        @SerializedName("message") var message: String? = null,
        @SerializedName("httpStatus") var httpStatus: String? = null,
        @SerializedName("timestamp") var timestamp: String? = null
)