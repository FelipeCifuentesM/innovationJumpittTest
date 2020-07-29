package com.example.graphicsinnovation.utils

import android.app.Activity
import android.content.Intent

//Go to second activity
inline fun <reified T: Activity> Activity.goToActivity(finish: Boolean = false, noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
    if (finish)
        finishAffinity()
}