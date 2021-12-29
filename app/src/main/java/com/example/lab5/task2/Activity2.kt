package com.example.lab5.task2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.MainApplication
import com.example.lab5.R
import java.io.InterruptedIOException
import java.net.URL
import java.util.concurrent.Future

class Activity2 : AppCompatActivity() {

    companion object {
        const val TAG = "Activity2"
    }

    val url = "https://www.dexerto.com/wp-content/uploads/2021/06/15/gamora-square-guardians.jpg"
    lateinit var futureTask: Future<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "CREATE")
        setContentView(R.layout.task234)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            try {
                Log.i(TAG, "START THREAD")
                futureTask = (application as MainApplication).executorService.submit {
                    val image =
                        BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
                    runOnUiThread {
                        imageView.setImageBitmap(image)
                    }
                }
                Log.i(TAG, "FINISH TASK")
            } catch (exception: InterruptedIOException) {
                Log.i(TAG, "INTERRUPT THREAD")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        futureTask.cancel(true)
        Log.i(TAG, "DESTROY")
    }
}