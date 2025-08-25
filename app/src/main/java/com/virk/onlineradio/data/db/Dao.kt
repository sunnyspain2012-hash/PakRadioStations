package com.virk.onlineradio.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY name")
    fun favorites(): Flow<List<FavoriteStation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(station: FavoriteStation)

    @Query("DELETE FROM favorites WHERE stationUuid = :uuid")
    suspend fun delete(uuid: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE stationUuid = :uuid)")
    fun isFavorite(uuid: String): Flow<Boolean>
}

@Dao
interface RecentDao {
    @Query("SELECT * FROM recents ORDER BY playedAt DESC LIMIT 100")
    fun recents(): Flow<List<RecentStation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recent: RecentStation)

    @Query("DELETE FROM recents")
    suspend fun clear()
}

