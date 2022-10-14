package com.kode.prageet.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.R
import com.kode.prageet.databinding.FragmentViewArtistFullBinding


class ViewArtistFull : Fragment(R.layout.fragment_view_artist_full) {
    private lateinit var binding: FragmentViewArtistFullBinding
    private lateinit var appStatusViewModel: AppStatusViewModel
    var position = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewArtistFullBinding.inflate(inflater)
        position = arguments?.getInt("position") ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appStatusViewModel = ViewModelProvider(requireActivity())[AppStatusViewModel::class.java]
        showfullArtists()
        var isShow = true
        var scrollRange = -1

        binding.artistFullAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.artistFullCBar.title = appStatusViewModel.clickPosition.value?.let {
                    appStatusViewModel.artistList.get(
                        it
                    ).tracks.get(0).artists
                }
                isShow = true
            } else if (isShow) {
                binding.artistFullCBar.title = appStatusViewModel.clickPosition.value?.let {
                    appStatusViewModel.artistList.get(
                        it
                    ).tracks.get(0).artists
                    //careful there should a space between double quote otherwise it wont work

                }
                isShow = false
            }
        })
    }

    fun showfullArtists() {
        binding.rcViewFullArtistList.adapter = FullArtistRecycler(appStatusViewModel, position)
        binding.rcViewFullArtistList.layoutManager = LinearLayoutManager(context)
    }


}