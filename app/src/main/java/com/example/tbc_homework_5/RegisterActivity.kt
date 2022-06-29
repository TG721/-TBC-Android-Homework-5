package com.example.tbc_homework_5

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //handle click, begin signup
        val nextButton: Button = findViewById(R.id.registerNextButton)
        nextButton.setOnClickListener {
            //validate data
            validateData()

        }
    }


    private fun validateData() {
        //get data
        email = (findViewById(R.id.registerEmail) as EditText).text.toString().trim()
        password = (findViewById(R.id.registerPassword) as EditText).text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //invalid email format
            (findViewById(R.id.registerError) as TextView).text = "Invalid email format"

        }
        else if(password.isEmpty()){
            //password is not entered
            (findViewById(R.id.registerError) as TextView).text = "Please Enter password"

        }
        else if (password.length<6){
            //password is less than 6
            (findViewById(R.id.registerError) as TextView).text = "Please must be atleast 6 characters long"

        }
        else {
            //data is valid, we continue sign up proccess
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
    //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //sing up success

                //get current use
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Almost done", Toast.LENGTH_SHORT ).show()
                val intent = Intent(this, RegisterPart2Activity::class.java)
                startActivity(intent)
                finish()

            }
            .addOnFailureListener{e->
                //sing up failed
                Toast.makeText(this, "Sign up failed due to ${e.message}", Toast.LENGTH_SHORT ).show()

            }
    }

}