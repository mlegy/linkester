package com.melegy.linkester.app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        val itemId = intent.data?.getQueryParameter("id")
        findViewById<TextView>(R.id.text_view).text = "Item details screen\nItem id: $itemId"
    }
}
