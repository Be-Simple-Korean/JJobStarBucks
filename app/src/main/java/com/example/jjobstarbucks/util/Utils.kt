package com.example.jjobstarbucks.util

import android.content.Context
import com.example.jjobstarbucks.model.Home
import com.google.gson.Gson

fun <T> Context.readData(filename: String, clazz: Class<T>): T? {
    return try {
        val inputs = this.resources.assets.open(filename)
        val buffer = ByteArray(inputs.available())
        inputs.read(buffer)
        inputs.close()

        Gson().fromJson(String(buffer), clazz)
    } catch (e: Exception) {
        null
    }
}

