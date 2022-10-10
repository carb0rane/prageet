package com.kode.proj1.Login.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.kode.proj1.Login.db.LoginViewModel
import com.kode.proj1.Login.db.UserProfile
import com.kode.proj1.databinding.ActivityRegistrationBinding

private const val TAG = "RegistrationActivity"
class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel=ViewModelProvider(this)[LoginViewModel::class.java]



binding.btnRegisterUser.setOnClickListener{
    val uInfo=UserProfile(0,binding.etRegisterUserEmail.text.toString(),binding.etRegisterUserPassword.text.toString())
    loginViewModel.registerUser(uInfo)
    Log.d(TAG, "Success ! User is: ${binding.etRegisterUserEmail.text.toString()} and ${binding.etRegisterUserPassword.text.toString()} ")
}



    }
}