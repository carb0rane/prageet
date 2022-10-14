package com.kode.prageet.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.databinding.SongrcviewBinding

private const val TAG = "MusicRecyclerView"

class MusicRecyclerView(vm: AppStatusViewModel
) : RecyclerView.Adapter<MusicRecyclerView.MusicViewHolder>() {
    val viewMO = vm

    inner class MusicViewHolder(val binding: SongrcviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            SongrcviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.binding.tvSongNameRecycler.text = viewMO.songlist.get(position).name
        holder.binding.tvArtistNameRecycler.text = viewMO.songlist.get(position).artists
        holder.binding.rcViewSongCardView.setOnClickListener {
            viewMO.rcViewSongCardClick(viewMO.songlist.get(position).path,viewMO.songlist.get(position).name,viewMO.songlist.get(position).artists)
        }
    }

    override fun getItemCount(): Int {
        return viewMO.songlist.size
    }
}