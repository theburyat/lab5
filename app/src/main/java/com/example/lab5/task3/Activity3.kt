package com.example.lab5.task3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab5.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class Activity3 : AppCompatActivity() {

    val url = "https://www.dexerto.com/wp-content/uploads/2021/06/15/gamora-square-guardians.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task234)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val image = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
                withContext(Dispatchers.Main) {
                    imageView.setImageBitmap(image)
                }
            }
        }
    }
}