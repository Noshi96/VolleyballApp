package com.papplications.volleyballteam.app.match.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.papplications.volleyballteam.app.match.converter.Converters
import com.papplications.volleyballteam.app.match.model.Match
import com.papplications.volleyballteam.app.match.model.MatchPlayerCrossRef
import com.papplications.volleyballteam.app.match.model.Set
import com.papplications.volleyballteam.app.match.model.Player

@Database(entities = [Match::class, Player::class, Set::class, MatchPlayerCrossRef::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MatchDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    companion object {
        @Volatile
        private var INSTANCE: MatchDatabase? = null

        fun getDatabase(context: Context): MatchDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchDatabase::class.java,
                    "match_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}