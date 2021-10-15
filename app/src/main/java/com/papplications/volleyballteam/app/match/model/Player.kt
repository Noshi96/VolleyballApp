package com.papplications.volleyballteam.app.match.model

import androidx.appcompat.widget.AppCompatImageView
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val nick: String,
    val img: String,
)