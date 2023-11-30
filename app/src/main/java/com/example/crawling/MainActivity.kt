package com.example.crawling
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleIntent(intent)
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

    }

}