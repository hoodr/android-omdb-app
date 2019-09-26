package com.example.walmartinhomedeliverytakehome.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.walmartinhomedeliverytakehome.model.Episode
import com.example.walmartinhomedeliverytakehome.model.Season
import com.example.walmartinhomedeliverytakehome.model.Show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.URLEncoder

/**
 * Plan:
 * - Fetch the series -> save the imdb id
 * - use that tha determine if other entries are in the db
 *
 * - Season == 1
 * - seriesID == from that
 *
 * Season = http://www.omdbapi.com/?apikey=9da50317&t=The+Office
 * Episodes = http://www.omdbapi.com/?apikey=9da50317&t=The+Office&Season=1
 */
class DataManager(private val apiService: OMDbApiService = RetrofitHelper.buildRetrofitService()) {
    val shows = MutableLiveData<MutableList<Show>>()
    val episodes = MutableLiveData<MutableList<Episode>>()

    fun fetchShow(name: String) {
        val urlEncodedName = URLEncoder.encode(name, "utf-8")

        Log.d(TAG, urlEncodedName)

        CoroutineScope(Dispatchers.IO).launch {

            val response = apiService.getShow(urlEncodedName)

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val show = response.body()
                        Log.d(TAG, show.toString())

                        response.body()?.let {
                            Log.d(TAG, it.toString())
                            val list = shows.value
                            list?.add(it)
                            shows.value = list
                        }
                    } else {
                        // toast("Error: ${response.code()}")
                        Log.e(TAG, "http response error code: ${response.code()}")
                    }

                } catch (e: HttpException) {
                    // toast("Exception ${e.message}")
                    Log.e(TAG, "Exception ${e.message}")

                } catch (e: Throwable) {
                    // toast("Ooops: Something else went wrong")
                    Log.e(TAG, "Big oof: ${e.message}")
                }
            }
        }
    }

    fun fetchSeason(number: String) : Season {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getSeason(number)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        Log.e(TAG, "http response error code: ${response.code()}")
                    }

                } catch (e: HttpException) {
                    Log.e(TAG, "Exception ${e.message}")

                } catch (e: Throwable) {
                    Log.e(TAG, "Big oof: ${e.message}")
                }
            }
        }

        return Season(mutableListOf(Episode(), Episode()))
    }

    fun fetchEpisode(imdbID: String) : Episode? {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getEpisode(imdbID)

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val episode = response.body()
                        Log.d(TAG, episode.toString())
                    } else {
                        Log.e(TAG, "http response error code: ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Log.e(TAG, "Exception ${e.message}")
                } catch (e: Throwable) {
                    Log.e(TAG, "Big oof: ${e.message}")
                }
            }
        }
        return Episode()
    }


    companion object {
        const val TAG: String = "DataManager"
    }
}