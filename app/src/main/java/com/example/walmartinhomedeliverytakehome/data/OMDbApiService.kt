package com.example.walmartinhomedeliverytakehome.data

import com.example.walmartinhomedeliverytakehome.model.Show
import com.example.walmartinhomedeliverytakehome.model.Episode
import com.example.walmartinhomedeliverytakehome.model.Season
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface OMDbApiService {

    /**
     *  "http://www.omdbapi.com/?apikey=$key&"
     * Return Season info
     *
     * Fetch the overall show info
     * Season:
     *     - Poster
     *     - Title
     */
    @GET("/?apikey=9da50317")
    suspend fun getShow(@Query("t") showName: String): Response<Show>

    /**
     *
     * Episode:
     * - title
     * - director
     * - writer
     * - plot
     * - poster
     */
    @GET("/?apikey=9da50317")
    suspend fun getEpisode(@Query("i") episodeId: String) : Response<Episode>

    /**
     *
     * Season
     * - Episodes
     *    - Episode
     *        - Title
     *        - imdbID
     *
     */
    @GET("/?apikey=9da50317&Season=1")
    suspend fun getSeason(@Query("t") showName: String) : Response<Season>
}