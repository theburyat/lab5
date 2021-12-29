package com.example.lab5.task1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.MainApplication
import com.example.lab5.R
import java.util.*
import java.util.concurrent.Future

class Activity1_2 : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var realElapsedSeconds: Int = 0
    var startTime: Long = 0
    var endTime: Long = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var futureTask: Future<*>

    companion object {
        const val SECONDS_COUNT = "SECONDS_COUNT"
        const val TAG = "Activity1_2"
    }

    private val executorThread = Runnable {
        try {
            Log.i(TAG, "LAUNCH THREAD")
            while (true) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed, ++secondsElapsed)
                }
            }
        } catch (exeption: InterruptedException) {
            Log.i(TAG, "INTERRUPT THREAD")
        }
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
        futureTask.cancel(true);
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "RESUME")
        startTime = Date().time
        futureTask = (application as MainApplication).executorService.submit(executorThread)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        sharedPreferences = getSharedPreferences(SECONDS_COUNT, Context.MODE_PRIVATE)
        secondsElapsed = sharedPreferences.getInt(SECONDS_COUNT, 0)
        realElapsedSeconds = sharedPreferences.getInt(SECONDS_COUNT, 0)

        textSecondsElapsed.setText(getString(R.string.seconds_elapsed, secondsElapsed))
    }
}