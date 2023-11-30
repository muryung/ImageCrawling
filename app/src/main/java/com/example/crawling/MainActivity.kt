package com.example.crawling
import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            handleIntent(intent)
        }.start() // TODO : error : android.os.NetworkOnMainThreadException

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.action?.let { action ->
            if (action == Intent.ACTION_SEND) {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let { sharedText ->
                    processUrl(sharedText)
                }
            }
        }
    }

    private fun processUrl(url: String) {
        val gridView: GridView = findViewById(R.id.gridView)
        gridView.adapter = GridAdapter(this, url)

    }

}