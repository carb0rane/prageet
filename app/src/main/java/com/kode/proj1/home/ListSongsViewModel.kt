package com.kode.proj1.home

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.kode.proj1.util.Song

class ListSongsViewModel(application: Application) : AndroidViewModel(application) {
    var mPlayer:MediaPlayer?=null



    fun initMediaPlayer(context: Context, uri: Uri):MediaPlayer?{
        mPlayer=MediaPlayer.create(context,uri)

        mPlayer?.start()
        return mPlayer
    }

    fun releaseMediaPlayer(){
        mPlayer?.release()
        mPlayer=null
    }

}