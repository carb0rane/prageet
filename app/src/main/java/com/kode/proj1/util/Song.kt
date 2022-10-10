package com.kode.proj1.util

import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "Song Class"
data class Song(
    val path:String,
    val name:String,
    val artists:String,
    val artistId:Int,
    val albumId:Int
){
    companion object {


        suspend fun getAllSongsList(cursor: Cursor?): Flow<Song> {
          //  Log.d(TAG, "musicc: I am now going to list music :)")

          //  Log.d(TAG, "Cursor is : $cursor")
            if (cursor != null) {
                cursor.moveToFirst()

                val songsFlow = flow {
                    do {
                        val songPath = cursor.getString(0)
                        val songName = cursor.getString(1)
                        val songArtists = cursor.getString(2) ?: "No Artist Found"
                        val artistId = cursor.getInt(3)
                        val albumId = cursor.getInt(4)

                    //    Log.d(TAG, "Song: $songName, AlbumID: $albumId ArtistID: $artistId")

                        emit(Song(songPath, songName, songArtists, albumId, artistId))
                        //     Log.d(TAG, "musicc: Successfully emitted ! ${name}")
                        //skjdf.add(Song(path, name))
//                    MediaScannerConnection.scanFile(
//                        applicationContext,
//                        arrayOf(path),
//                        null,
//                        object : MediaScannerConnectionClient {
//                            override fun onMediaScannerConnected() {}
//                            override fun onScanCompleted(path: String?, uri: Uri?) {
//                                //  Log.d(TAG, "onScanCompleted: the path is $path and uri is $uri")
//                            }
//                        })
                        //Log.d(TAG, "onCreate: The cursor is $path")
                        //path = cursor.getColumnIndex(MediaStore.Audio.Media.DATA)
                    } while (cursor.moveToNext())

                }
                Log.d(TAG, "getAllSongsList: 😀😎")
                return songsFlow
            }

            return flow {
                emit(Song("null", "null", "null", -1, -1))
            }

        }

        suspend fun enlistAllSongs(song: Song,songList:MutableList<Song>) {
//            val p = CoroutineScope(Dispatchers.IO).async {
//                getAllSongsList(cursor)
//            }
           // val songFlow = getAllSongsList(cursor)

          //  songFlow.collect {
            if (song.artists!="<unknown>")
              //  Log.d(TAG, "$song")

                songList.add(song)
              //  isUpdated.postValue(isUpdated.value?.plus(1))


       //     }

        }
    }

}