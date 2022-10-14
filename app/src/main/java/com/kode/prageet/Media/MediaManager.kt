package com.kode.prageet.Media

import android.database.Cursor
import com.kode.prageet.util.Album
import com.kode.prageet.util.Artist
import com.kode.prageet.util.Song

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