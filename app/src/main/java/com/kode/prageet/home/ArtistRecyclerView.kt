package com.kode.prageet.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.databinding.ArtistRcViewBinding

class ArtistRecyclerView(
    viewModel: AppStatusViewModel
):RecyclerView.Adapter<ArtistRecyclerView.ArtistViewHolder>() {
    val viewModel=viewModel
    inner class ArtistViewHolder(val binding: ArtistRcViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(ArtistRcViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.binding.tvArtistNameRecycler.text=viewModel.artistList.get(position).tracks.get(0).artists
        holder.binding.imgViewArtistRcView.setOnClickListener {
            viewModel.showFull(position)
        }

    }

    override fun getItemCount(): Int {
       return viewModel.artistList.size
    }



}