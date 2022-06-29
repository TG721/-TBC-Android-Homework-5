package com.example.tbc_homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tbc_homework_5.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

//FirebaseAuth
private lateinit var firebaseAuth : FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //buttons click handling
        binding.fromMainLoginButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        binding.fromMainRegisterButton.setOnClickListener{
            val intent=Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkUser() {
            //if user is already logged in
            //go to LoggedIn/profile activity

            //get current user
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser !=null){
                //user is already logged in
                startActivity(Intent(this, LoggedInActivity::class.java))
                finish()
            }

    }
}