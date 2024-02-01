package com.assigment.simioseiSot.ViewModel

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.assigment.simioseiSot.LogInActivity
import com.assigment.simioseiSot.R
import com.assigment.simioseiSot.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {
    private val binding : ActivityWelcomeScreenBinding by lazy {
        ActivityWelcomeScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
Handler(Looper.getMainLooper()).postDelayed({
    startActivity(Intent(this,LogInActivity::class.java))
    finish()
},3000)


        val welcomeText = "Welcome"
        val spannableString = SpannableString(welcomeText)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#1400FF")),0,5,0)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#4317A1")),5,welcomeText.length,0)

        binding.welcomeText.text = spannableString
    }
}