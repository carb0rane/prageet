package com.kode.prageet.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.databinding.SongrcviewBinding

class ViewAlbumFullRecycler(viewModel: AppStatusViewModel, position: Int) :
    RecyclerView.Adapter<ViewAlbumFullRecycler.FullAlbumViewHolder>() {
    val viewModel = viewModel
    val _position = position

    inner class FullAlbumViewHolder(val binding: SongrcviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullAlbumViewHolder {
        return FullAlbumViewHolder(
            SongrcviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FullAlbumViewHolder, position: Int) {
        holder.binding.tvSongNameRecycler.text =
            viewModel.albumList.get(_position).tracks.get(position).name
        holder.binding.rcViewSongCardView.setOnClickListener {
            viewModel.rcViewSongCardClick(
                viewModel.albumList.get(_position).tracks.get(position).path,
                viewModel.albumList.get(_position).tracks.get(position).name,
                viewModel.albumList.get(_position).tracks.get(position).artists
            )
        }
    }

    override fun getItemCount(): Int {
        return viewModel.albumList.get(_position).tracks.size
    }
}