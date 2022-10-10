package com.kode.proj1.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kode.proj1.AppStatus.AppStatusViewModel
import com.kode.proj1.Media.MediaManager
import com.kode.proj1.R
import com.kode.proj1.util.Song
import com.kode.proj1.databinding.FragmentListSongsBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow

private const val TAG = "listSongs"

class listSongs : Fragment(R.layout.fragment_list_songs) {
    
    private lateinit var songsFlow: Flow<Song>
    private lateinit var skjdf: MutableList<Song>
    private lateinit var binding:FragmentListSongsBinding
    private lateinit var homeViewModel: ListSongsViewModel
    private lateinit var appStatusViewModel: AppStatusViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentListSongsBinding.inflate(inflater)
        Log.d(TAG, "onCreateView: <=> ")
        return binding.root
           
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel=ViewModelProvider(this)[ListSongsViewModel::class.java]
        appStatusViewModel=ViewModelProvider(requireActivity())[AppStatusViewModel::class.java]
        Log.d(TAG, "ListSongFragment ! 😋😋😋 ")

        showsongs()
       // CoroutineScope(IO).launch{ appStatusViewModel.getSongList() }
        activity?.let {
            appStatusViewModel.isUpdated.observe(it){
                binding.rcFragmentListSongs.adapter?.notifyDataSetChanged()
            }
        }
        Log.d(TAG, "ListSongFragment ! 😋😋😋 $$ END ")


    }

    fun showsongs() {
        newMusicObserver()
        Log.d(TAG, "Now I am in show songs !! ")
       // appStatusViewModel.songlist = skjdf
        val objj = MusicRecyclerView(appStatusViewModel)
        binding.rcFragmentListSongs.adapter = objj
        binding.rcFragmentListSongs.layoutManager = LinearLayoutManager(activity)
        Log.d(TAG, "ListSongFragment ! 😋😋😋  ENDOFRC ")
    }
    // Observer pattern to check weather new song has been added or not !
    fun newMusicObserver(){
        CoroutineScope(Dispatchers.IO).launch {
            var prev=0
            while(true){
             delay(64)
               if(MediaManager.new > prev) {
                   withContext(Main) {
                      // binding.rcFragmentListSongs.adapter?.notifyDataSetChanged()
                       binding.rcFragmentListSongs.adapter?.notifyItemChanged(50)
                   }
                   prev=MediaManager.new
               }
            }
        }
    }

}