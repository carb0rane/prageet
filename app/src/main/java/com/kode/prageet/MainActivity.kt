package com.kode.prageet


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.databinding.ActivityMainBinding
import com.kode.prageet.home.home_fragment
import com.kode.prageet.search.SearchFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

private const val TAG = "MainActivity"

open class MainActivity : AppCompatActivity() {
    private lateinit var appStatusViewModel: AppStatusViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        runBlocking {
            getpermission()
        }

        Log.d(TAG, "Mainactivity  <=> \uD83D\uDE0E\uD83D\uDE0E\uD83E\uDD23 ")
        appStatusViewModel = ViewModelProvider(this@MainActivity)[AppStatusViewModel::class.java]
        appStatusViewModel.context = this

        appStatusViewModel.getSongList()
        Log.d(TAG, "Mainactivity  <=> \uD83D\uDE0E\uD83D\uDE0E\uD83E\uDD23 <=>  ")
        // val ijdd= AppStatus(1,false,"thisaintpoor")
        // appStatusViewModel.addAppStatus(ijdd)
        appStatusViewModel.currentlyPlaying.observe(this) {
            binding.mediaPlayer.tvBottomMediaPlayerSongName.text = it
        }
        appStatusViewModel.currentArtist.observe(this) {
            binding.mediaPlayer.tvBottomMediaPlayerArtistName.text = it
        }



        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_activity_frameLayout, home_fragment())
            commit()
        }


        Log.d(TAG, "Mainactivity  <=> \uD83D\uDE0E\uD83D\uDE0E\uD83E\uDD23  ^^^ ")
        binding.mediaPlayer.tvBottomMediaPlayerSongName.isSelected = true
        binding.mediaPlayer.imgbtnBottomMediaPlayerPlayPause.setOnClickListener {
            if (appStatusViewModel.mPlayer != null) {
                if (appStatusViewModel.playWhenReady == true) {
                    appStatusViewModel.mPlayer!!.pause()
                    appStatusViewModel.playWhenReady = false
                    val animshake = android.view.animation.AnimationUtils.loadAnimation(
                        this,
                        R.anim.button_click
                    )
                    binding.mediaPlayer.imgbtnBottomMediaPlayerPlayPause.startAnimation(animshake)

                    binding.mediaPlayer.imgbtnBottomMediaPlayerPlayPause.setImageResource(R.drawable.ic_play_arrow_24)
                } else if (appStatusViewModel.playWhenReady == false) {
                    appStatusViewModel.mPlayer!!.start()
                    appStatusViewModel.playWhenReady = true
                    val animshake = android.view.animation.AnimationUtils.loadAnimation(
                        this,
                        R.anim.button_click
                    )
                    binding.mediaPlayer.imgbtnBottomMediaPlayerPlayPause.startAnimation(animshake)
                    binding.mediaPlayer.imgbtnBottomMediaPlayerPlayPause.setImageResource(R.drawable.ic_pause_24)
                }
            }
        }
        binding.mediaPlayer.imgbtnBottomMediaPlayerFavourite.setOnClickListener {
            val animshake =
                android.view.animation.AnimationUtils.loadAnimation(this, R.anim.button_click)
            binding.mediaPlayer.imgbtnBottomMediaPlayerFavourite.startAnimation(animshake)

            //  getpermission()

            Log.d(TAG, "Mainactivity  <=> \uD83D\uDE0E\uD83D\uDE0E\uD83E\uDD23 $$$ END ")
            // sleep(5000)

        }
        binding.bottomNavMain.menu.get(0).setOnMenuItemClickListener {
            binding.bottomNavMain.menu.get(0).isChecked = true
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_activity_frameLayout, home_fragment())
               // addToBackStack("a_fragment")
                commit()
            }
            true
        }
        binding.bottomNavMain.menu.get(1).setOnMenuItemClickListener{
            binding.bottomNavMain.menu.get(1).isChecked = true
           supportFragmentManager.beginTransaction().apply {
               replace(R.id.main_activity_frameLayout, SearchFragment())
               //addToBackStack("b_fragment")
               commit()
           }
            true
       }
    }

        fun getpermission() {

            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                    if (isGranted) {
                        Log.d(TAG, "getpermission: We now have the permission")
                        // musicc()
                    } else {
                        Log.d(TAG, "getpermission: Failed to Get the Permission")
                    }
                }

            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (requestPermissionLauncher == null) {
                    Log.d(TAG, "getpermission: Some Serious Issue asking for permission !  ")
                }

                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (requestPermissionLauncher == null) {
                    Log.d(TAG, "getpermission: Some Serious Issue asking for permission !  ")
                }
                requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            } else {
                Log.d(
                    TAG,
                    "getpermission: All necessary permissions have been granted , Further execution should begin! "
                )
                //  musicc()
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>, grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            when (requestCode) {
                requestCode -> {
                    // If request is cancelled, the result arrays are empty.
                    if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    ) {

                        Log.d(TAG, "onRequestPermissionsResult: We nw have he permisson")
                        //   musicc()
                        // Permission is granted. Continue the action or workflow
                        // in your app.
                    } else {
                        // Explain to the user that the feature is unavailable because
                        // the features requires a permission that the user has denied.
                        // At the same time, respect the user's decision. Don't link to
                        // system settings in an effort to convince the user to change
                        // their decision.
                        Log.d(TAG, "onRequestPermissionsResult: Failed to get the permission")
                    }
                    return
                }

                // Add other 'when' lines to check for other
                // permissions this app might request.
                else -> {
                    Log.d(TAG, "onRequestPermissionsResult: Can't continue using the app ")
                    // Ignore all other requests.
                }
            }
        }

        override fun onStart() {
            super.onStart()


        }

        public override fun onResume() {
            super.onResume()

//        if (Util.SDK_INT <= 23 || binding.mediaView.player == null) {
//            Log.d(TAG, "onResume: This is OnResume")
//            initplayer()
//        }

        }


//    public override fun onPause() {
//        super.onPause()
//        if (Util.SDK_INT <= 23) {
//            Log.d(TAG, "onPause: This is Onpause")
//            appStatusViewModel.releasePlayer()
//        }
//    }

//    public override fun onStop() {
//        super.onStop()
//        if (Util.SDK_INT > 23) {
//            Log.d(TAG, "onStop: This is OnStop")
//            appStatusViewModel.releasePlayer()
//        }
//    }


}