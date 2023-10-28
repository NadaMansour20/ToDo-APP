package com.android.todo_app

import java.util.Calendar

fun Calendar.clearTime():Calendar{
    this.clear(Calendar.HOUR)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.MINUTE)

    return this
}