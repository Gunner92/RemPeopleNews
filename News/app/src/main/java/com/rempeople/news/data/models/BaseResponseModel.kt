package com.rempeople.news.data.models

class BaseResponseModel<T>(data: T? ,error: Throwable? ) {
    var response = data
    var error: Throwable? = error
}