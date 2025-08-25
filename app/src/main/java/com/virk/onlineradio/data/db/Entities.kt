package com.virk.onlineradio.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteStation(
    @PrimaryKey val stationUuid: String,
    val name: String,
    val streamUrl: String?,
    val favicon: String?
)

@Entity(tableName = "recents")
data class RecentStation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val stationUuid: String?,
    val name: String,
    val streamUrl: String?,
    val favicon: String?,
    val playedAt: Long
)

