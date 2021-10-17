package com.papplications.volleyballteam.app.match.model

import androidx.room.Entity

@Entity(primaryKeys = ["matchId", "playerId"])
data class MatchPlayerCrossRef(
    val matchId: Int,
    val playerId: Int
)