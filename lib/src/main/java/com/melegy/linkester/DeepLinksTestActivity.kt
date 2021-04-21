package com.melegy.linkester

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.melegy.linkester.internal.Link
import com.melegy.linkester.internal.LinksParser
import com.melegy.linkester.internal.lazyUnsafe
import com.melegy.linkester.internal.triggerDeepLink

internal class DeepLinksTestActivity : Activity() {

    private val linksListView by lazyUnsafe { findViewById<ListView>(R.id.linkester_links_list) }
    private val autoCollectedLinksButton by lazyUnsafe { findViewById<Button>(R.id.linkester_auto_collected_links_button) }
    private val manuallyCollectedLinksButton by lazyUnsafe { findViewById<Button>(R.id.linkester_manually_collected_links_button) }
    private val tryLinkButton by lazyUnsafe { findViewById<Button>(R.id.linkester_try_link_button) }
    private val linkEditText by lazyUnsafe { findViewById<EditText>(R.id.linkester_link_edit_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linkester_activity_deep_links_test)
        actionBar?.title =
            "${packageManager.getApplicationLabel(applicationInfo)} linkester"
        val links = LinksParser(this)
        val linksList = mutableListOf(*links.manuallyAdded.toTypedArray())

        val adapter = object : ArrayAdapter<Link>(
            this,
            android.R.layout.simple_list_item_2,
            android.R.id.text1,
            linksList
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val titleTextView = view.findViewById<TextView>(android.R.id.text1)
                val linkTextView = view.findViewById<TextView>(android.R.id.text2)
                val link = linksList[position].link
                val name = linksList[position].name
                titleTextView.text = name
                linkTextView.text = link
                view.setOnClickListener { triggerDeepLink(linksList[position].link) }
                view.setOnLongClickListener {
                    linkEditText.setText(link)
                    linkEditText.setSelection(link.length)
                    true
                }
                return view
            }
        }

        linksListView.adapter = adapter

        fun updateListUpdater(links: List<Link>) {
            linksList.apply {
                clear()
                linksList.addAll(links)
            }
            adapter.notifyDataSetChanged()
        }

        autoCollectedLinksButton.setOnClickListener {
            autoCollectedLinksButton.isSelected = true
            manuallyCollectedLinksButton.isSelected = false

            updateListUpdater(links.autoCollected)
        }

        manuallyCollectedLinksButton.setOnClickListener {
            autoCollectedLinksButton.isSelected = false
            manuallyCollectedLinksButton.isSelected = true

            updateListUpdater(links.manuallyAdded)
        }

        manuallyCollectedLinksButton.isSelected = true
        tryLinkButton.setOnClickListener { triggerDeepLink(linkEditText.text.toString()) }
    }
}
