package com.example.lab5.task3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab5.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.URL

class Activity3 : AppCompatActivity() {

    companion object {
        const val TAG = "Activity3"
    }

    lateinit var job: Job
    val url = "https://www.dexerto.com/wp-content/uploads/2021/06/15/gamora-square-guardians.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "CREATE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task234)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Log.i(TAG, "START COROUTINE")
            job = lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val image =
                        BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
                    withContext(Dispatchers.Main) {
                        imageView.setImageBitmap(image)
                    }
                    Log.i(TAG, "COROUTINE ENDED ITS WORK")
                } catch (exception: Exception) {
                    Log.i(TAG, "SOMETHING WENT WRONG WITH COROUTINE")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "DESTROY")
        Log.i(TAG, "COROUTINE IS CANCELLED? ${job.isCancelled}")
    }
}