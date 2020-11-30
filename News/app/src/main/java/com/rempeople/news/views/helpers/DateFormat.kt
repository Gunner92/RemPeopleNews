package com.rempeople.news.views.helpers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class DateFormat {
    companion object {
        public fun formatDate(date: String): String {
            var output: String = ""
            try {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
                output = formatter.format(parser.parse(date))
            } catch (ex: ParseException) {
                output = ""
            }
            return output
        }

        public fun formatDate(date: Date): String {
            var output: String = ""
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
            output = formatter.format(date)
            return output
        }
    }

}