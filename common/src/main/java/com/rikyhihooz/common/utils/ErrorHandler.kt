package com.rikyhihooz.common.utils

import com.rikyhihooz.model.ErrorResponse

data class ErrorHandler(
    val errorType: ErrorType,
    val errorResponse: ErrorResponse?
) {
    enum class ErrorType {
        SNACKBAR,
        LAYOUT
    }
}