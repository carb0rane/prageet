package com.kode.prageet.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.databinding.SongrcviewBinding
import com.kode.prageet.util.Song

private const val TAG = "SearchRecycler Class"
class SearchRecycler(result: List<Song>, appStatusViewModel:AppStatusViewModel) : RecyclerView.Adapter<SearchRecycler.SearchViewHolder>() {
    val result  = result
    val appStatusViewModel = appStatusViewModel
    inner class SearchViewHolder(val binding: SongrcviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(SongrcviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        try {
            holder.binding.tvSongNameRecycler.text = result.get(position).name
            holder.binding.tvArtistNameRecycler.text = result.get(position).artists
            holder.binding.rcViewSongCardView.setOnClickListener {
                appStatusViewModel.rcViewSongCardClick(result.get(position).path,result.get(position).name, result.get(position).artists)
            }
        }
        catch (e:Exception){
            Log.d(TAG, "onBindViewHolder: raised $e")
        }
    }

    override fun getItemCount(): Int {
             try {
                 return result.size
             }
             catch (e:Exception){
                 Log.d(TAG, "getItemCount: $e")
             }
        return 0  
    }
}