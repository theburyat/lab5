package com.example.lab5.task1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab5.R
import kotlinx.coroutines.delay
import java.util.*

class Activity1_3: AppCompatActivity() {
    var secondsElapsed: Int = 0
    var realElapsedSeconds: Int = 0
    var startTime: Long = 0
    var endTime: Long = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val SECONDS_COUNT = "SECONDS_COUNT"
        const val TAG = "Activity1_1"
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "PAUSE")
        endTime = Date().time
        realElapsedSeconds += ((endTime - startTime) / 1000).toInt()
        with(sharedPreferences.edit()) {
            putInt(SECONDS_COUNT, realElapsedSeconds)
            apply()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "RESUME")
        startTime = Date().time
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        sharedPreferences = getSharedPreferences(SECONDS_COUNT, Context.MODE_PRIVATE)
        secondsElapsed = sharedPreferences.getInt(SECONDS_COUNT, 0)
        realElapsedSeconds = sharedPreferences.getInt(SECONDS_COUNT, 0)

        textSecondsElapsed.setText(getString(R.string.seconds_elapsed, secondsElapsed))

        lifecycleScope.launchWhenResumed {
            while (true) {
                delay(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed, ++secondsElapsed)
                }
            }
        }
    }
}