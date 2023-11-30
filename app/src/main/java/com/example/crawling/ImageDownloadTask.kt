package com.example.crawling

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageDownloadTask {
    suspend fun downloadImage(imageUrl: String): Bitmap? = withContext(Dispatchers.IO) {
        var bitmap: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.connect()

            val input: InputStream = connection.inputStream

            bitmap = BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext bitmap
    }
}