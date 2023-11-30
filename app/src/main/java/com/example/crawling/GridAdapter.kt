package com.example.crawling

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException


class GridAdapter(private val c: Context, private val imageUrl : String) : BaseAdapter() {
    override fun getCount(): Int = 1
    override fun getItem(position: Int): Any? = null
    override fun getItemId(position: Int): Long  = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var imageView : ImageView

        if (convertView == null) {
            imageView = ImageView(c)
            imageView.layoutParams =
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView = convertView as ImageView
        }

        var doc : Document
        try {
            doc = Jsoup.connect(imageUrl).get()
            val links: Elements = doc.select("a[href]")
            for (link in links) {
                val innerurl = link.text()
                if (!innerurl.contains("://")) {
                    continue
                }

                val innerDoc: Document = Jsoup.connect(innerurl).get()
                val imgLinks: Elements = innerDoc.select("img[src]")
                for (innerLink in imgLinks) {
                    val innerImgSrc = innerLink.attr("src")
                    Picasso.get().load(innerImgSrc).into(imageView)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return imageView
    }
}