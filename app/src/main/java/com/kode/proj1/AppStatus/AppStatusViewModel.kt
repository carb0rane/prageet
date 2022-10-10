package com.kode.proj1.AppStatus

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.kode.proj1.Media.MediaManager
import com.kode.proj1.util.Album
import com.kode.proj1.util.Artist
import com.kode.proj1.util.Song
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

private const val TAG = "AppStatusViewModel"
class AppStatusViewModel(application: Application) : AndroidViewModel(application) {

    private val appStatusRepo: AppStatusRepo
    var isUserLoggedIn: Boolean = false
    var player: ExoPlayer? = null
    var mPlayer:MediaPlayer?=null
    var playWhenReady = true
    var currentItem = 0
    var playbackPosition = 0L
     var songlist:MutableList<Song> = arrayListOf()
     var artistList:MutableList<Artist> = arrayListOf(Artist(arrayListOf(Song("Test","Ram Jai Ram","Hanuman",4,5))))
     var albumList:MutableList<Album> = arrayListOf(Album(arrayListOf(Song("Test","Ram Jai Ram","Hanuman",4,5))))
    val currentlyPlaying=MutableLiveData<String>()
    val currentArtist=MutableLiveData<String>()
    val isUpdated=MutableLiveData<Int>()
    val clickPosition = MutableLiveData<Int>()

    lateinit var context:Context

    fun setSong(songName:String,artistName:String){
        currentlyPlaying.value=songName
        currentArtist.value=artistName
    }

     fun getSongList(){
         CoroutineScope(Dispatchers.IO).launch{
             MediaManager.getMedia(songlist,albumList,artistList,getCursor()!!)
         }


    }
    fun showFull(position: Int){
        clickPosition.value = position
    }



fun rcViewSongCardClick(path:String,name:String,artists:String){
    releaseMediaPlayer()
    val uri= Uri.parse("file://"+path)
    setSong(name,artists)
    initMediaPlayer(context,uri)
}



    init {
        val appStatusDao = AppStatusDatabase.getAppStatFromDataBase(application).appStatusDao()
        appStatusRepo = AppStatusRepo(appStatusDao)

    }

     fun getCursor(): Cursor? {
        Log.d(TAG, "getCursor ~ ")
        val cursor = context.contentResolver?.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns.ARTIST_ID),
            null,
            null,
            MediaStore.Audio.Media.DATA
        )
        Log.d(TAG, "getCursor: $cursor")
        return cursor

    }


    fun addAppStatus(appStatus: AppStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            // isUserLoggedIn=appStatusRepo.isUserLoggedIn()
            appStatusRepo.addAppStatus(appStatus)
        }
    }

    fun initializePlayer(context: Context,uri:String):ExoPlayer?{
     //   collectFlow()
        Log.d(TAG, "initializePlayer: Hello now i am going to initialize the player")
        Log.d(TAG, "playbackPosition: $playbackPosition currentItem: $currentItem playWhenReady: $playWhenReady")

         player=ExoPlayer.Builder(context).build()
            .also{
                val mediaItem= MediaItem.fromUri("file://"+uri)
                it.setMediaItem(mediaItem)
                it.playWhenReady=playWhenReady
                //it.seekTo(currentItem,playbackPosition)
                it.prepare()
            }

        return player
    }
    fun initMediaPlayer(context: Context,uri:Uri):MediaPlayer?{
        mPlayer=MediaPlayer.create(context,uri)

        mPlayer?.start()
        return mPlayer
    }

     fun releasePlayer() {
         Log.d(TAG, "releasePlayer: Hello I have been called to release the player !")
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }

         Log.d(TAG, "playbackPosition: $playbackPosition currentItem: $currentItem playWhenReady: $playWhenReady")
        player = null
    }
    fun releaseMediaPlayer(){
        mPlayer?.release()
        mPlayer=null
    }

    val startflow = flow<Int>{
                     val start=20
                     var curr=start
        while(curr>0){
            delay(1000)
            emit(curr)
            curr--
        }
    }
    fun collectFlow(){
        viewModelScope.launch {
            startflow.collect{
                time ->
                Log.d(TAG, "collectFlow: The time is $time ")

            }
        }
    }


}