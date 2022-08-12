package com.gamingingrs.roomwithhilt_example

import android.content.Context
import android.widget.Toast
import java.util.*

fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}

fun getNames(e: Class<out Enum<*>>) =
    Arrays.stream(e.enumConstants).map(Enum<*>::name).toArray<String>(::arrayOfNulls)