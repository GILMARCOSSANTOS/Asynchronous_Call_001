package com.example.asynchronouscalls001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    /* Global Variables Scope: */
    private var timer001: Double = 0.00
    private var timer002: Double = 0.00
    private lateinit var textViewTimer001: MaterialTextView
    private lateinit var textViewTimer002: MaterialTextView
    private lateinit var buttonTimer001: MaterialButton
    private lateinit var buttonTimer002: MaterialButton
    private lateinit var buttonResetTime: MaterialButton
    private lateinit var buttonCallBack: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Execution of Functions: */
        globalVariables()
        buttonTimer001()
        buttonTimer002()
        resetTime()
        updateTimers()
        buttonCallBack()
    }

    private fun buttonTimer001() {

        buttonTimer001.setOnClickListener {
            lifecycleScope.launch {
                incrementBefore001()
            }
        }
    }

    private fun buttonTimer002() {

        buttonTimer002.setOnClickListener {
            lifecycleScope.launch {
                incrementBefore002()
            }
        }
    }

    private fun resetTime() {

        buttonResetTime.setOnClickListener {
            lifecycleScope.launch {
                resetTimers()
            }
        }
    }

    private fun resetTimers() {

        timer001 = 0.00
        timer002 = 0.00

        updateTimers()
    }

    private fun updateTimers() {

        textViewTimer001.text = timer001.toString()
        textViewTimer002.text = timer002.toString()
    }

    private suspend fun incrementBefore001() {
        delay(3000)
        timer001 += 1.0
        updateTimers()
    }

    private suspend fun incrementBefore002() {
        delay(5000)
        timer002 += 1.0
        updateTimers()
    }

    private fun buttonCallBack() {
        buttonCallBack.setOnClickListener {
            if (buttonCallBack.isClickable) {
                val intent = Intent(this, ActivityCallBack::class.java).apply {
                }
                startActivity(intent)
            }

        }
    }

    private fun globalVariables() {
        textViewTimer001 = findViewById(R.id.txtVw_time001_actvtMain)
        textViewTimer002 = findViewById(R.id.txtVw_time002_actvtMain)
        buttonTimer001 = findViewById(R.id.bttn_time001_actvtMain)
        buttonTimer002 = findViewById(R.id.bttn_time002_actvtMain)
        buttonResetTime = findViewById(R.id.bttn_reset_actvtMain)
        buttonCallBack = findViewById(R.id.bttn_callBack_actvtMain)
    }
}