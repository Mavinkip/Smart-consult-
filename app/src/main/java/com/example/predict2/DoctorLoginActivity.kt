package com.example.predict2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DoctorLoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var doctorEmailEditText: EditText
    private lateinit var doctorPasswordEditText: EditText
    private lateinit var doctorLoginButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var signupPrompt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_login)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Bind views from layout
        doctorEmailEditText = findViewById(R.id.doctorEmailEditText)
        doctorPasswordEditText = findViewById(R.id.doctorPasswordEditText)
        doctorLoginButton = findViewById(R.id.doctorLoginButton)
        progressBar = findViewById(R.id.progressBar)
        signupPrompt=findViewById(R.id.signupPrompt)


        // Handle doctor login button click
        doctorLoginButton.setOnClickListener { loginUser() }
        signupPrompt.setOnClickListener{
            startActivity(Intent(this, DoctorRegisterActivity::class.java))
        }
    }


    private fun loginUser() {
        val email = doctorEmailEditText.text.toString().trim()
        val password = doctorPasswordEditText.text.toString().trim()

        if (validateData(email, password)) {
            loginUserInFirebase(email, password)
        }
    }

    private fun validateData(email: String, password: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            doctorEmailEditText.error = "Email is invalid"
            return false
        }
        if (password.length < 6) {
            doctorPasswordEditText.error = "Password too short"
            return false
        }
        return true
    }

    private fun loginUserInFirebase(email: String, password: String) {
        changeInProgress(true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                changeInProgress(false)
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, PatientListActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun changeInProgress(inProgress: Boolean) {
        progressBar.visibility = if (inProgress) View.VISIBLE else View.GONE
        doctorLoginButton.visibility = if (inProgress) View.GONE else View.VISIBLE
    }
}
