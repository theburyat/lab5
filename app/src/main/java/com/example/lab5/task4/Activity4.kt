package com.example.lab5.task4

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.lab5.R

class Activity4 : AppCompatActivity() {

    val url = "https://www.dexerto.com/wp-content/uploads/2021/06/15/gamora-square-guardians.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task234)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            imageView.load(url)
        }
    }
}