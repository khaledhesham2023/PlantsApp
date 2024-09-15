package com.khaledamin.plantsapp.util

sealed class ViewState<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<out T>(data: T?, message: String? = null) : ViewState<T>(data, message)
    class Error : ViewState<Nothing>()
}