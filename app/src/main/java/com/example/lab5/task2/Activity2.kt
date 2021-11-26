package com.example.lab5.task2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.R
import java.net.URL
import java.util.concurrent.Executors

class Activity2: AppCompatActivity() {

    val url = "https://www.dexerto.com/wp-content/uploads/2021/06/15/gamora-square-guardians.jpg"
    val executorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task234)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            executorService.execute {
                val image = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
                runOnUiThread {
                    imageView.setImageBitmap(image)
                }
            }
        }
    }
}