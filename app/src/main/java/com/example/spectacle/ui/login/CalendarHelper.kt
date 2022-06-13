package com.example.spectacle.ui.login

import java.util.*

// ktlint-disable no-wildcard-imports

object CalendarHelper {

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun getCurrentDateInMills(): Long {
        return Calendar.getInstance().timeInMillis
    }
}
