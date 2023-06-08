package com.example.jjobstarbucks.util

import android.content.Context
import com.example.jjobstarbucks.model.Home
import com.google.gson.Gson

fun Context.readData(): Home? {
    return try {
        val inputs = this.resources.assets.open("home.json")
        val buffer = ByteArray(inputs.available())
        inputs.read(buffer)
        inputs.close()

        Gson().fromJson(String(buffer), Home::class.java)
    } catch (e: Exception) {
        null
    }
}

