package com.kode.proj1.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.kode.proj1.AppStatus.AppStatusViewModel
import com.kode.proj1.databinding.FragmentViewAlbumFullBinding


class ViewAlbumFullFragment : Fragment() {
    private lateinit var binding: FragmentViewAlbumFullBinding
    private lateinit var appStatusViewModel: AppStatusViewModel
var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          binding = FragmentViewAlbumFullBinding.inflate(inflater)
        position = arguments?.getInt("position") ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appStatusViewModel = ViewModelProvider(requireActivity())[AppStatusViewModel::class.java]
        var isShow = true
        var scrollRange = -1

        binding.AlbumFullAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.AlbumFullCBar.title = appStatusViewModel.clickPosition.value?.let {
                    appStatusViewModel.artistList.get(
                        it
                    ).tracks.get(0).artists
                }
                isShow = true
            } else if (isShow) {
                binding.AlbumFullCBar.title = appStatusViewModel.clickPosition.value?.let {
                    appStatusViewModel.artistList.get(
                        it
                    ).tracks.get(0).artists
                    //careful there should a space between double quote otherwise it wont work

                }
                isShow = false
            }
        })


         showFullAlbum()
    }

    fun showFullAlbum(){
        binding.rcViewFullAlbumList.adapter=ViewAlbumFullRecycler(appStatusViewModel,position)
        binding.rcViewFullAlbumList.layoutManager = LinearLayoutManager(context)

    }


}