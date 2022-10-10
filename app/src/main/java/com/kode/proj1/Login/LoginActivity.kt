package com.kode.proj1.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.kode.proj1.Login.db.LoginViewModel
import com.kode.proj1.Login.db.UserProfile
import com.kode.proj1.Login.registration.RegistrationActivity
import com.kode.proj1.MainActivity
import com.kode.proj1.databinding.ActivityLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private var useefs: UserProfile? = null
    //  private var pass = "tests"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }


        binding.btnLoginUser.setOnClickListener {
            GlobalScope.launch {
                userLogin()
            }
        }
    }

    suspend fun userLogin() {
        val uu = UserProfile(
            1,
            binding.etLoginEmailAddress.text.toString(),
            binding.etLoginPassword.text.toString()
        )
        val pass = loginViewModel.logincheck(uu)
        if (pass == binding.etLoginPassword.text.toString()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}