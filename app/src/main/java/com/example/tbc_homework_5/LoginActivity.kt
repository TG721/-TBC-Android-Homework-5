package com.example.tbc_homework_5

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_homework_5.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth



//FirebaseAuth
private lateinit var firebaseAuth : FirebaseAuth
private var email = ""
private var password = ""

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        //button click handling
        val  logInButton: Button = findViewById(R.id.loginButton)
        validateData()


    }

    private fun validateData() {
        //get data
    email = (findViewById(R.id.loginEmail) as EditText).text.toString().trim()
    password = (findViewById(R.id.loginPassword) as EditText).text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            (findViewById(R.id.emailError) as TextView).error = "Invalid email format"

        }
        else if (password.isEmpty()){
            //no password was entered
            (findViewById(R.id.passwordError) as TextView).error = "Please enter password"
        }
        else {
            //data is validated, we begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login success

                //get user info
                val firebaseUser  = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()

                //go to LoggedIn/profile activity
                startActivity(Intent(this, LoggedInActivity::class.java))
                finish()


            }
            .addOnFailureListener{ e->
                //login failed
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()


            }
    }


}