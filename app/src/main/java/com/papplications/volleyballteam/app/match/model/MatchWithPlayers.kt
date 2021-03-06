package com.papplications.volleyballteam.app.match.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MatchWithPlayers(
    @Embedded val match: Match,
    @Relation(
        parentColumn = "matchId",
        entityColumn = "playerId",
        associateBy = Junction(MatchPlayerCrossRef::class)
    )
    val players: List<Player>

)