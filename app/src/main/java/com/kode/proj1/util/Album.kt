package com.kode.proj1.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlin.system.measureTimeMillis

private const val TAG = "Album Class"
data class Album(
    val tracks:ArrayList<Song>,
) {
    companion object{
        fun getTotalSongs():Int{
         return 5
        }
        suspend fun splitSongs(song:Song,albumList:MutableList<Album>):Int{

                try {
                    for ((index,album) in albumList.withIndex()) {
                        if (song.albumId == album.tracks.get(0).albumId) {

                            album.tracks.add(song)
                           return index
                        }
                    }
                    val album = Album(arrayListOf(song))
                    val index = albumList.lastIndex
                    albumList.add(index,album)
                    return index

                } catch (e: Exception) {
                    Log.d(TAG, "splitSongs: raised $e")
                }
return 0

          //  Log.d(TAG, "splitSongs: It took <=> $time_splitSongs")
           // Log.d(TAG, "splitSongs: Recieved Null AlbumList $albumList")
        }
        suspend fun albumManager(albumList: MutableList<Album>, songList: MutableList<Song>,isUpdated: MutableLiveData<Int>){
            for (song in songList){
             //   Log.d(TAG, "albumManager: $song")
                splitSongs(song,albumList)
            }
            Log.d(TAG, "albumManager: $albumList")
        }
    }

}