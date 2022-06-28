package com.example.tbc_homework_5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val  nextButton: Button = findViewById(R.id.registerNextButton)
        nextButton.setOnClickListener {
            val intent= Intent(this, RegisterPart2Activity::class.java)
            startActivity(intent)
        }
    }
}