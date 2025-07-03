package com.example.expensetracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.databinding.ActivitySignInSuccessBinding

class SignInSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")

        binding.tvWelcome.text = "Welcome, $firstName $lastName!"
        binding.tvUsername.text = "Username: $username"
    }
}
