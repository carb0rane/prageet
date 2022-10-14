package com.kode.prageet.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    val fraglist: MutableList<Fragment> = arrayListOf()
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return listSongs()
            1 -> return listArtistFragment()
            2 -> return listAlbumFragment()
            3 -> return PlaylistFragment()
            else -> return listSongs()
        }

    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    suspend fun getFragment() {
        fraglist.add(0, listSongs())
        fraglist.add(1, listSongs())
        fraglist.add(2, listAlbumFragment())
        fraglist.add(3, listAlbumFragment())
    }


}