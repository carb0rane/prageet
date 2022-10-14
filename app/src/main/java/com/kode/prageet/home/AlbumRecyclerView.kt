package com.kode.prageet.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.databinding.SongrcviewBinding

class AlbumRecyclerView(
    viewModel: AppStatusViewModel
):RecyclerView.Adapter<AlbumRecyclerView.AlbumViewHolder>() {
    val viewModel = viewModel
    inner class AlbumViewHolder(val binding:SongrcviewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
       return AlbumViewHolder(SongrcviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.tvSongNameRecycler.text=viewModel.albumList.get(position).tracks.get(0).albumId.toString()
        holder.binding.rcViewSongCardView.setOnClickListener {
      viewModel.showFull(position)
        }
    }

    override fun getItemCount(): Int {
       return viewModel.albumList.size
    }
}