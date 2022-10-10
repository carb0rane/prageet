package com.kode.proj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.kode.proj1.AppStatus.AppStatus
import com.kode.proj1.AppStatus.AppStatusViewModel
import com.kode.proj1.Login.LoginActivity
import java.sql.Time

private const val TAG = "SplashActivity"
class SplashActivity : AppCompatActivity() {
    private lateinit var appStatusViewModel: AppStatusViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Starting to get the time  <=> 😎😎🤣 ")
//        appStatusViewModel= ViewModelProvider(this)[AppStatusViewModel::class.java]
//        val ijdd= AppStatus(1,false,"thisaintpoor")
//        appStatusViewModel.addAppStatus(ijdd)
//
//        Log.d("This SplashActivity", "onCreate: The user is not LoggesIn : ${appStatusViewModel.isUserLoggedIn} ")
//        if(appStatusViewModel.isUserLoggedIn==false) {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
//        else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
       // }


        finish()
    }
}