package com.papplications.volleyballteam.app.player.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.papplications.volleyballteam.R
import com.papplications.volleyballteam.app.feature.draw.view.ChoosePlayersFragmentDirections
import com.papplications.volleyballteam.app.player.model.Player
import kotlinx.android.synthetic.main.custom_player_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var playerList = emptyList<Player>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_player_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playerList[position]
        holder.itemView.textView_name_in_row.text = currentItem.name
        holder.itemView.row_layout.setOnClickListener {
            val action = ChoosePlayersFragmentDirections.actionChoosePlayersFragmentToUpdatePlayerFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(players: List<Player>) {
        this.playerList = players
        notifyDataSetChanged()
    }
}