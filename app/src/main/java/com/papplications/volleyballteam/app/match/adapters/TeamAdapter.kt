package com.papplications.volleyballteam.app.match.adapters

import kotlinx.android.synthetic.main.custom_player_row.view.imageView_avatar
import kotlinx.android.synthetic.main.custom_player_row.view.textView_name_in_row

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.papplications.volleyballteam.R
import com.papplications.volleyballteam.app.player.model.Player

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.MyViewHolder>() {

    private var playerList = emptyList<Player>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_player_in_match_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]
        holder.itemView.textView_name_in_row.text = currentItem.name
        when (currentItem.name) {
            "Mateusz(Kikis)" -> {
                holder.itemView.imageView_avatar.setImageResource(R.drawable.kikis)
            }
            "Pawel" -> {
                holder.itemView.imageView_avatar.setImageResource(R.drawable.pawel)
            }
            "Paweł" -> {
                holder.itemView.imageView_avatar.setImageResource(R.drawable.pawel)
            }
            else -> {
                holder.itemView.imageView_avatar.setImageResource(R.drawable.ic_baseline_person_24)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(players: List<Player>) {
        this.playerList = players
        notifyDataSetChanged()
    }
}