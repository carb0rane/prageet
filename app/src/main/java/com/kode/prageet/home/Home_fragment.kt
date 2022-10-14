package com.kode.prageet.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.kode.prageet.R
import com.kode.prageet.databinding.FragmentHomeBinding

private const val TAG = "Home_fragment"

class home_fragment : Fragment(R.layout.fragment_home) {
private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "HomeFragment   <=>  😀😀")
//        childFragmentManager.beginTransaction().apply {
//            replace(R.id.homeFrameLayout,listSongs())
//            commit()
//        }
        val viewAdapter=HomeViewPagerAdapter(activity as AppCompatActivity)
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d(TAG, "onViewCreated: Chi")
//            viewAdapter.getFragment()
//        }

        binding.vieuPager.adapter=viewAdapter
        binding.vieuPager.offscreenPageLimit=3
        Log.d(TAG, "HomeFragment   <=>  😀😀")

        TabLayoutMediator(binding.tabLayout,binding.vieuPager){
                tab,pos->
            tab.text=getTabTitle(pos)
        }.attach()
        Log.d(TAG, "HomeFragment   <=>  😀😀 $$ END")
    }

    private fun getTabTitle(position:Int):String{
        return when(position){
            0-> "Tracks"
            1-> "Artist"
            2-> "Album"
            3-> "Playlist"
            else -> "Invalid Tab ! "
        }
    }
}