package com.kode.prageet.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.databinding.SongrcviewBinding

class FullArtistRecycler(viewModel: AppStatusViewModel, position: Int) :
    RecyclerView.Adapter<FullArtistRecycler.FullArtistViewHolder>() {
    val viewModel = viewModel
    val _position = position

    inner class FullArtistViewHolder(val binding: SongrcviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullArtistViewHolder {
        return FullArtistViewHolder(
            SongrcviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FullArtistViewHolder, position: Int) {
        holder.binding.tvSongNameRecycler.text =
            viewModel.artistList.get(_position).tracks.get(position).name
        holder.binding.rcViewSongCardView.setOnClickListener {
            viewModel.rcViewSongCardClick(
                viewModel.artistList.get(_position).tracks.get(position).path,
                viewModel.artistList.get(_position).tracks.get(position).name,
                viewModel.artistList.get(_position).tracks.get(position).artists
            )
        }
    }

    override fun getItemCount(): Int {
        return viewModel.artistList.get(_position).tracks.size
    }
}