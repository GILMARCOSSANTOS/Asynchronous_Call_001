package com.example.asynchronouscalls001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ActivityCallBack : AppCompatActivity() {

    /* Global Variables Scope: */
    private lateinit var buttonClickHere: MaterialButton
    private lateinit var textViewResult: MaterialTextView
    private lateinit var editTextEnterText: EditText
    private lateinit var editTextEnterLetter: EditText
    private lateinit var textViewText: MaterialTextView
    private lateinit var buttonBackCoroutines: MaterialButton
    private lateinit var letter: String
    private lateinit var word: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callback)

        /* Execution of Functions: */
        globalVariables()
        buttonClick()
        backToCoroutines()
    }

    private fun processText() {

        word = editTextEnterText.text.toString()
        letter = editTextEnterLetter.text.toString()

        if (word.contains(letter)) {
            textViewText.text = getString(R.string.a_frase_tem_letra)
        } else {
            textViewText.text = buildString {
                append("A frase não tem letra: '")
                append(letter)
                append("'")
            }
        }
    }

    private fun buttonClick() {

        buttonClickHere.setOnClickListener {

            processText()

            someFunction(object : SomeCallback {
                override fun onSuccess() {
                    Toast.makeText(this@ActivityCallBack, "Inside Success", Toast.LENGTH_LONG)
                        .show()

                    textViewResult.text = letter
                }

                override fun onFailure(error: String) {
                    Toast.makeText(
                        this@ActivityCallBack,
                        "Inside Failure - $error",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })
        }
    }

    fun someFunction(someCallback: SomeCallback) {

        if (word.contains(letter)) {
            someCallback.onSuccess()
        } else {
            someCallback.onFailure("$word does not contain $letter")
        }
    }

    interface SomeCallback {
        fun onSuccess()

        fun onFailure(error: String)
    }

    private fun backToCoroutines() {

        buttonBackCoroutines.setOnClickListener {
            if (buttonBackCoroutines.isClickable) {

                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
        }
    }

    private fun globalVariables() {

        textViewResult = findViewById(R.id.txtVw_result_actvtCallBack_id)
        textViewText = findViewById(R.id.txtVw_theText_actvtCallBack_id)
        buttonClickHere = findViewById(R.id.btton_clicHere_actvtCallBack_001)
        buttonBackCoroutines = findViewById(R.id.bttn_backActivity_actvtCallBack_)
        editTextEnterText = findViewById(R.id.edtTxt_enterText_actvtCallBacl_id)
        editTextEnterLetter = findViewById(R.id.edtTxt_enterLetter_actvtCallBacl_id)
    }
}