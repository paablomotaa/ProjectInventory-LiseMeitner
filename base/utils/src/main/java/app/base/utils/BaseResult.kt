package app.base.utils

sealed class BaseResult <out T>{
    data class Success <T>(val data: T) : BaseResult<T>()
    data class Error(var message: Exception) : BaseResult<Nothing>()
}