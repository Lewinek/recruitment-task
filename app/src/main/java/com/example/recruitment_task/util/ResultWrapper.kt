package com.example.recruitment_task.util



sealed class ResultWrapper<out T> {
    object Loading : ResultWrapper<Nothing>()
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val message: String, val exception: Throwable? = null) : ResultWrapper<Nothing>()
}

inline fun <T> ResultWrapper<T>.onSuccess(action: (T) -> Unit) : ResultWrapper<T>{
    if (this is ResultWrapper.Success) action(data)
    return this
}

inline fun <T> ResultWrapper<T>.onError(action: (String) -> Unit) : ResultWrapper<T>{
    if (this is ResultWrapper.Error) action(message)
    return this
}



