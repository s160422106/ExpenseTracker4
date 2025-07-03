package com.example.expensetracker4.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker4.databinding.ActivitySignInSuccessBinding

class SignInSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent (jika masih ada)
        val username = intent.getStringExtra("username")
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")

        // Tampilkan di UI
        binding.tvWelcome.text = "Welcome, $firstName $lastName!"
        binding.tvUsername.text = "Username: $username"

        // Logout button
        binding.btnLogout.setOnClickListener {
            // Hapus SharedPreferences
            val sharedPref = getSharedPreferences("session", MODE_PRIVATE)
            with(sharedPref.edit()) {
                clear()
                apply()
            }

            // Kembali ke halaman login
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
