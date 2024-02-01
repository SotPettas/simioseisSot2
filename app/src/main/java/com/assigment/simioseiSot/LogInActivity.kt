package com.assigment.simioseiSot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.assigment.simioseiSot.databinding.ActivityLogInBinding
import com.assigment.simioseiSot.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInActivity : AppCompatActivity() {
    private val binding: ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        // initialization Firebase Auth
        auth = FirebaseAuth.getInstance()








        binding.loginButton.setOnClickListener {

            val userName :String = binding.userName.text.toString()
            val password :String = binding.password.text.toString()

            if (userName.isEmpty() || password.isEmpty()){

                Toast.makeText(this, "Please Fill all Fields", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword( userName ,password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Sign-in Successful", Toast.LENGTH_SHORT).show()
                           startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Sign-in Failed :${task.exception?.message}", Toast.LENGTH_SHORT).show()

                        }
                    }
            }

        }

        binding.signUpButton.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }



    }
}