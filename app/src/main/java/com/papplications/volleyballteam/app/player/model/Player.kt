package com.papplications.volleyballteam.app.player.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val img: String,
) : Parcelable