package com.kode.prageet.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.Media.MediaManager
import com.kode.prageet.R
import com.kode.prageet.databinding.FragmentListAlbumBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "listArtistFragment"

class listAlbumFragment : Fragment(R.layout.fragment_list_artist) {
    lateinit var appStatusViewModel: AppStatusViewModel
    private lateinit var binding: FragmentListAlbumBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListAlbumBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appStatusViewModel = ViewModelProvider(requireActivity())[AppStatusViewModel::class.java]
        // CoroutineScope(Dispatchers.IO).launch { appStatusViewModel.getAlbumList() }
        //  Log.d(TAG, "onViewCreated: ${appStatusViewModel.artistList} ")
        appStatusViewModel.isUpdated.observe(viewLifecycleOwner) {
            binding.rcFragmentListAlbums.adapter?.notifyDataSetChanged()
        }

        appStatusViewModel.clickPosition.observe(viewLifecycleOwner) {

            parentFragmentManager.beginTransaction().apply {
                val bundle = Bundle()
                bundle.putInt("position", it)
                val artistFragment = ViewAlbumFullFragment()
                artistFragment.arguments = bundle
                replace(R.id.main_activity_frameLayout, artistFragment)
                addToBackStack(null)
                commit()
            }
        }

        showAlbums()

    }

    fun showAlbums() {
        newMusicObserver()
        binding.rcFragmentListAlbums.adapter = AlbumRecyclerView(appStatusViewModel)
        binding.rcFragmentListAlbums.layoutManager = LinearLayoutManager(activity)
    }

    fun newMusicObserver() {
        CoroutineScope(Dispatchers.IO).launch {
            var prev = 0
            while (true) {
                // delay(64)
                if (MediaManager.new > prev) {
                    withContext(Dispatchers.Main) {
                        binding.rcFragmentListAlbums.adapter?.notifyDataSetChanged()
                    }
                    prev = MediaManager.new
                }
            }
        }
    }

}