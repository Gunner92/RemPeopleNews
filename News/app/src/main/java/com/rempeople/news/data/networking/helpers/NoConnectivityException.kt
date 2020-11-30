package com.rempeople.news.data.networking.helpers

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No Internet connection"
}