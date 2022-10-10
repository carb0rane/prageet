package com.kode.proj1.Media

import android.database.Cursor
import android.util.Log
import com.kode.proj1.home.listSongs
import com.kode.proj1.util.Album
import com.kode.proj1.util.Artist
import com.kode.proj1.util.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

private const val TAG = "MediaManager Class"

class MediaManager {

    companion object {
        var new = 0
        suspend fun getMedia(
            songs: MutableList<Song>,
            albums: MutableList<Album>,
            artists: MutableList<Artist>,
            cursor: Cursor
        ) {
            val flow = Song.getAllSongsList(cursor)
            flow
                .collect {
                    Song.enlistAllSongs(it, songs)
                    Album.splitSongs(it, albums)
                    Artist.splitArtist(it, artists)
                    new++
                }
        }
    }
}