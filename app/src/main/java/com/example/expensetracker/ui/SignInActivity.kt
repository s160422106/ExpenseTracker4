package com.example.expensetracker4.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker4.data.MyDatabase
import com.example.expensetracker4.data.repository.UserRepository
import com.example.expensetracker4.data.viewmodel.AuthViewModel
import com.example.expensetracker4.data.viewmodel.AuthViewModelFactory
import com.example.expensetracker4.databinding.ActivitySignInBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = MyDatabase.getInstance(applicationContext)
        userRepository = UserRepository(database.userDao())
        val factory = AuthViewModelFactory(userRepository)
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.btnSignIn.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            authViewModel.signIn(username, password)
        }

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        lifecycleScope.launch {
            authViewModel.loginSuccess.collectLatest { success ->
                when (success) {
                    true -> {
                        // login sukses
                        // dapatkan username & password yang terakhir dipakai login
                        val username = binding.etUsername.text.toString().trim()
                        val password = binding.etPassword.text.toString()

                        val user = userRepository.getUser(username, password)
                        if (user != null) {
                            val intent = Intent(
                                this@SignInActivity,
                                SignInSuccessActivity::class.java
                            ).apply {
                                putExtra("username", user.username)
                                putExtra("firstName", user.firstName)
                                putExtra("lastName", user.lastName)
                            }
                            startActivity(intent)
                            finish()
                        }
                        // Reset status supaya event tidak berulang
                        authViewModel.resetLoginStatus()
                    }

                    false -> {
                        // login gagal, tampilkan toast
                        Toast.makeText(this@SignInActivity, "Login failed", Toast.LENGTH_SHORT)
                            .show()
                        // Reset status supaya event tidak berulang
                        authViewModel.resetLoginStatus()
                    }

                    null -> {
                        // belum ada login, biasanya abaikan saja
                    }
                }
            }
        }
    }
}