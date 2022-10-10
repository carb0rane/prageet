package com.kode.proj1.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kode.proj1.AppStatus.AppStatusViewModel
import com.kode.proj1.Media.MediaManager
import com.kode.proj1.R
import com.kode.proj1.databinding.FragmentListArtistBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "listArtistFragment"
class listArtistFragment : Fragment(R.layout.fragment_list_artist) {
  lateinit var appStatusViewModel: AppStatusViewModel
  private lateinit var binding: FragmentListArtistBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding=FragmentListArtistBinding.inflate(inflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    appStatusViewModel=ViewModelProvider(requireActivity())[AppStatusViewModel::class.java]
  showArtist()
    appStatusViewModel.clickPosition.observe(viewLifecycleOwner){

      parentFragmentManager.beginTransaction().apply {
        val bundle = Bundle()
        bundle.putInt("position",it)
        val artistFragment=ViewArtistFull()
        artistFragment.arguments=bundle
        replace(R.id.main_activity_frameLayout,artistFragment)
        addToBackStack(null)
        commit()
      }
    }
  }

  fun showArtist(){
    newMusicObserver()
         binding.rcFragmentListArtists.adapter=ArtistRecyclerView(appStatusViewModel)
          binding.rcFragmentListArtists.layoutManager=GridLayoutManager(context,2)
  }

  override fun onResume() {
     super.onResume()
   // binding.rcFragmentListArtists.adapter?.notifyDataSetChanged()
  }
  fun newMusicObserver(){
    CoroutineScope(Dispatchers.IO).launch {
      var prev=0
      while(true){
        // delay(64)
        if(MediaManager.new > prev) {
          withContext(Main) {
            binding.rcFragmentListArtists.adapter?.notifyDataSetChanged()
          }
          prev=MediaManager.new
        }
      }
    }
  }


}