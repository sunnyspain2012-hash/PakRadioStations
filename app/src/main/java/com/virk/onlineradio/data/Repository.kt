package com.virk.onlineradio.data

import android.content.Context
import com.virk.onlineradio.data.api.RadioBrowserApi
import com.virk.onlineradio.data.api.StationDto
import com.virk.onlineradio.data.db.AppDatabase
import com.virk.onlineradio.data.db.FavoriteStation
import com.virk.onlineradio.data.db.RecentStation
import kotlinx.coroutines.flow.Flow

class Repository(
    context: Context,
    private val api: RadioBrowserApi
) {
    private val db = AppDatabase.get(context)
    private val favoriteDao = db.favoriteDao()
    private val recentDao = db.recentDao()

    suspend fun search(name: String?, country: String?, language: String?, limit: Int = 100): List<StationDto> =
        api.searchStations(name = name, country = country, language = language, limit = limit)

    fun favorites(): Flow<List<FavoriteStation>> = favoriteDao.favorites()
    fun isFavorite(uuid: String): Flow<Boolean> = favoriteDao.isFavorite(uuid)

    suspend fun toggleFavorite(dto: StationDto) {
        if (dto.stationUuid.isBlank()) return
        val fav = FavoriteStation(dto.stationUuid, dto.name, dto.urlResolved, dto.favicon)
        favoriteDao.upsert(fav)
    }

    suspend fun removeFavorite(uuid: String) = favoriteDao.delete(uuid)

    fun recents(): Flow<List<RecentStation>> = recentDao.recents()
    suspend fun addRecent(dto: StationDto) {
        val recent = RecentStation(
            stationUuid = dto.stationUuid,
            name = dto.name,
            streamUrl = dto.urlResolved,
            favicon = dto.favicon,
            playedAt = System.currentTimeMillis()
        )
        recentDao.insert(recent)
    }
}

