package com.example.tbc_homework_5

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterPart2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_part_2)

        (findViewById(R.id.singUpButton) as Button).setOnClickListener {
            startActivity(Intent(this, LoggedInActivity::class.java))
            finish()
        }

    }
}