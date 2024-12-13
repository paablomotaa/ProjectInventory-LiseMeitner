package app.base.utils

sealed class BaseResult <out T>{
    data class Success<T>(var data: T): BaseResult<T>()
    data class Error(val exception: Exception): BaseResult<Nothing>()
}