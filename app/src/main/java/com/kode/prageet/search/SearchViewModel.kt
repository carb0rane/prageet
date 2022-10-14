package com.kode.prageet.search

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.provider.MediaStore.Audio.AudioColumns
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kode.prageet.util.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

private const val TAG = "SearchViewModel"

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var context: Context
    var searchResult = MutableLiveData<Song>()
    var songList: ArrayList<Song> = arrayListOf()


    suspend fun getTextQuery(flow: Flow<CharSequence?>) {
        flow
            .filter { it?.length!! >= 1 }
            .debounce(100)
            .flatMapLatest { getCursor(it) }
            .collect {
                if (it != null) {
                    songList.clear()
                    while (it.moveToNext()) {
                        try {
                            searchResult.postValue(
                                Song(
                                    it.getString(0),
                                    it.getString(1),
                                    it.getString(2),
                                    it.getInt(3),
                                    it.getInt(4)
                                )
                            )
                            songList.add(
                                Song(
                                    it.getString(0) ?: "null",
                                    it.getString(1) ?: "null",
                                    it.getString(2) ?: "null",
                                    it.getInt(3) ?: 1,
                                    it.getInt(4) ?: 1
                                )
                            )
                        } catch (e: Exception) {
                            Log.d(TAG, " raised : $e")
                        }
                    }

                }
            }
    }

    suspend fun getCursor(query: CharSequence?): Flow<Cursor?> {
        return flow<Cursor?> {
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver?.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.AudioColumns.TITLE,
                        MediaStore.Audio.AudioColumns.ARTIST,
                        MediaStore.Audio.AudioColumns.ALBUM_ID,
                        MediaStore.Audio.AudioColumns.ARTIST_ID
                    ),
                    "${AudioColumns.TITLE} LIKE ?",
                    arrayOf("%$query%"),
                    MediaStore.Audio.Media.DATA
                )
            } catch (e: Exception) {
                Log.d(TAG, "getCursor: $e")
            }
            emit(cursor)
        }
    }
}