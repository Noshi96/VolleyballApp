package com.papplications.volleyballteam.app.match.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.papplications.volleyballteam.R
import com.papplications.volleyballteam.app.player.model.Player
import kotlinx.android.synthetic.main.custom_player_in_match_row.view.*
import kotlinx.android.synthetic.main.custom_player_row.view.textView_name_in_row

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.MyViewHolder>() {

    private var playerList = emptyList<Player>()
    private var chosenTeam = "0"
    private var showAvatars = false

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_player_in_match_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]
        holder.itemView.text_view_number.text = chosenTeam
        holder.itemView.textView_name_in_row.text = currentItem.name
        if (showAvatars) {


            when (currentItem.name) {
                "Mateusz(Kikis)" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.kikis)
                }
                "Kikis" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.kikis)
                }
                "Pawel" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.pawel)
                }
                "Paweł" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.pawel)
                }
                "Pati" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.pati)
                }
                "Klaudia" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.klaudia)
                }
                "Mateusz" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.mateusz)
                }
                "Łukasz" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.lukasz)
                }
                "Sara" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.sara)
                }
                "Sandra" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.sandra)
                }
                "Ania" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.ania)
                }
                "Oskar" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.oskar)
                }
                "Damian" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.damian)
                }
                "Oliwia" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.olivia)
                }
                "Koala" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.michal)
                }
                "Jędrzej" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.jedrzej)
                }
                "Patison" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.patison)
                }
                "Rafał" -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.rafal)
                }
                else -> {
                    holder.itemView.imageView_avatar_inside.setImageResource(R.drawable.ic_baseline_person_24)
                }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(players: List<Player>, chosenTeam: String, showAvatars: Boolean) {
        this.playerList = players
        this.chosenTeam = chosenTeam
        this.showAvatars = showAvatars
        notifyDataSetChanged()
    }
}