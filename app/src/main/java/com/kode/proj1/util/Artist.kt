package com.kode.proj1.util

import android.util.Log

private const val TAG = "Artist Class"

data class Artist(
    val tracks: ArrayList<Song>,

    ) {
    companion object {

        suspend fun splitArtist(
            song: Song,
            artistlist: MutableList<Artist>

        ) {
            try {
                // var found = false
                for (artist in artistlist) {
                    // Log.d(TAG, "splitArtist: $artist")
                    if (song.artistId == artist.tracks.get(0).artistId) {
                      //  found = true

                        artist.tracks.add(song)
                        // artistlist.add(artist)
                          return
                    }
                }
                  //if (found == false) {
                      val artist = Artist(arrayListOf(song))
                      artistlist.add(artist)
                      return
                  //}

            } catch (e: Exception) {
                Log.d(TAG, "splitArtist: Raised:$e")
            }


        }

//        suspend fun manageArtists(artistlist: MutableList<Artist>, albumList: MutableList<Album>) {
//            if (artistlist != null) {
//                albumList.forEach {
//                    splitArtist(artistlist, it)
//                }
//            } else {
//                //Log.d(TAG, "manageArtists: Empty Artist list recieved")
//            }
//        }

    }

}