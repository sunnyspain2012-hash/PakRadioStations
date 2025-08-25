package com.virk.onlineradio.data.api

import com.squareup.moshi.Json
import retrofit2.http.GET
import retrofit2.http.Query

data class StationDto(
    @Json(name = "stationuuid") val stationUuid: String,
    @Json(name = "name") val name: String,
    @Json(name = "url_resolved") val urlResolved: String?,
    @Json(name = "favicon") val favicon: String?,
    @Json(name = "country") val country: String?,
    @Json(name = "state") val state: String?,
    @Json(name = "language") val language: String?,
    @Json(name = "tags") val tags: String?
)

interface RadioBrowserApi {
    // See https://www.radio-browser.info/webservice
    @GET("/json/stations/search")
    suspend fun searchStations(
        @Query("name") name: String? = null,
        @Query("country") country: String? = null,
        @Query("language") language: String? = null,
        @Query("limit") limit: Int = 100
    ): List<StationDto>

    @GET("/json/countries")
    suspend fun countries(): List<CountryDto>
}

data class CountryDto(
    @Json(name = "name") val name: String,
    @Json(name = "stationcount") val stationCount: Int
)

