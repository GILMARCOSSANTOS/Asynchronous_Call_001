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
    private var clickCountDouble001: Double = 0.00
    private var clickCountDouble002: Double = 0.00
    private lateinit var textViewClickCount001: MaterialTextView
    private lateinit var textViewClickCount002: MaterialTextView
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
        buttonResetTimer()
        updateClickCountDouble()
        buttonCallBack()
    }

    private fun buttonTimer001() {

        buttonTimer001.setOnClickListener {
            lifecycleScope.launch {
                incrementTime001()
            }
        }
    }

    private fun buttonTimer002() {

        buttonTimer002.setOnClickListener {
            lifecycleScope.launch {
                incrementTime002()
            }
        }
    }

    private fun buttonResetTimer() {

        buttonResetTime.setOnClickListener {
            lifecycleScope.launch {
                resetTimersDouble()
            }
        }
    }

    private fun resetTimersDouble() {

        clickCountDouble001 = 0.00
        clickCountDouble002 = 0.00

       updateClickCountDouble()
    }

    private fun updateClickCountDouble() {

        textViewClickCount001.text = clickCountDouble001.toString()
        textViewClickCount002.text = clickCountDouble002.toString()
    }

    private suspend fun incrementTime001() {
        delay(3000)
        clickCountDouble001 += 1.0
        updateClickCountDouble()
    }

    private suspend fun incrementTime002() {
        delay(5000)
        clickCountDouble002 += 1.0
        updateClickCountDouble()
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
        textViewClickCount001 = findViewById(R.id.txtVw_time001_actvtMain)
        textViewClickCount002 = findViewById(R.id.txtVw_time002_actvtMain)
        buttonTimer001 = findViewById(R.id.bttn_time001_actvtMain)
        buttonTimer002 = findViewById(R.id.bttn_time002_actvtMain)
        buttonResetTime = findViewById(R.id.bttn_reset_actvtMain)
        buttonCallBack = findViewById(R.id.bttn_callBack_actvtMain)
    }
}