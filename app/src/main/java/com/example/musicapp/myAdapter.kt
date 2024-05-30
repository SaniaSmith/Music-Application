package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class myAdapter(val context : Activity, val dataList : List<Data>) :
    RecyclerView.Adapter<myAdapter.myViewHolder>(){

    var playSong: Data? = null
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentData = dataList[position]

        holder.title.text = currentData.title

        Picasso.get().load(currentData.album.cover).into(holder.image)

        holder.play.setOnClickListener {
            if (playSong != currentData) {
                mediaPlayer?.pause()
                mediaPlayer?.release()

                mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
                playSong = currentData
            }

            mediaPlayer?.start()
        }

        holder.pause.setOnClickListener {
            if (playSong == currentData) {
                mediaPlayer?.pause()
            }
        }
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val title: TextView
        val play: ImageButton
        val pause: ImageButton

        init {
            image = itemView.findViewById(R.id.iv_musicImage)
            title = itemView.findViewById(R.id.tv_musicTitle)
            play = itemView.findViewById(R.id.btnPlay)
            pause = itemView.findViewById(R.id.btnPause)
        }
    }
}