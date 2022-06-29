package com.example.tbc_homework_5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoggedInActivity: AppCompatActivity() {

    //Firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logged_in)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle logout button
        (findViewById(R.id.log_out) as Button).setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }




    private fun checkUser() {
        //if user is already logged in
        //go to LoggedIn/profile activity

        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser !=null){
            //user is already logged in
            //get user info
            var email = firebaseUser.email
            //set textview to user email
            (findViewById(R.id.loginInfo) as TextView).text=email
        }
        else {
            //user is null and not logged in, go to main page
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
    }
}