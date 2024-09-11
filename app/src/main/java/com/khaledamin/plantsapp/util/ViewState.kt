package com.khaledamin.plantsapp.util

import com.khaledamin.plantsapp.*

sealed class ViewState<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<out T>(data: T?, message: String? = null) : ViewState<T>(data, message)
    class Error(message: String?) : ViewState<Nothing>()
}