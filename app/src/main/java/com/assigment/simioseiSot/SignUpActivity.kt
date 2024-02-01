package com.assigment.simioseiSot

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.assigment.simioseiSot.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
       ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize Firebase Authentication = auth

        auth = FirebaseAuth.getInstance()

        binding.signInButton.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        binding.registerButton.setOnClickListener {

            // get text from input-text fields

           val email :String = binding.email.text.toString()
           val userName :String = binding.userName.text.toString()
           val password :String = binding.password.text.toString()
           val repeatPassword :String = binding.repeatPassword.text.toString()

            // check if there is any blank field
            if(email.isEmpty()|| userName.isEmpty()|| password.isEmpty()|| repeatPassword.isEmpty()){

                Toast.makeText(this, "Please Fill all Fields", Toast.LENGTH_SHORT).show()
                
            }else if (password != repeatPassword){
                Toast.makeText(this, "Password must be the same", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LogInActivity::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Registration Failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }

                    }

            }
        }
    }
}