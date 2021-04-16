package com.melegy.linkester.internal

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast

internal fun <T> lazyUnsafe(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

internal fun Activity.triggerDeepLink(link: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(link)
            setPackage(packageName)
        }
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "This link is not handled by your App!", Toast.LENGTH_SHORT).show()
    }
}
