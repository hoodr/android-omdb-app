package com.example.walmartinhomedeliverytakehome.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walmartinhomedeliverytakehome.data.DataManager

import com.example.walmartinhomedeliverytakehome.model.Episode
import com.example.walmartinhomedeliverytakehome.model.Show
import com.example.walmartinhomedeliverytakehome.util.OnFragmentInteractionListener
import com.example.walmartinhomedeliverytakehome.adapter.ShowAdapter

/**
 * The Shared View Model for the home fragment of the Main Activity
 *
 * Here The observable data will be:
 *  - What fragment to show
 *  - What Show (according to user input)
 *  - What Episode (according to user input)
 *
 * Control user flow through the app via the fragments
 *
 *  Home Fragment -> Show -> Episodes -> Episode Detail
 *  Favorites -> Favorites Fragment (Just a list)
 *  Settings -> Setting Fragment
 *
 */
class HomeSharedViewModel(
    private val dataManager: DataManager = DataManager()
) : ViewModel(), OnFragmentInteractionListener {

    private var showAdapter: ShowAdapter
    private var listener: OnFragmentInteractionListener? = null

    private val initialShows: MutableList<String> = mutableListOf(
        "The Office",
        "Parks and Recreation",
        "The Good Place",
        "Unbreakable Kimmy Schmidt"
    )

    val shows = MutableLiveData<MutableList<Show>>()
    val episodes = MutableLiveData<MutableList<Episode>>()
    val selectedShow = MutableLiveData<Show>()
    val selectedEpisode = MutableLiveData<Episode>()
    val currFragment = MutableLiveData<String>() // Record the fragment that is currently on top

    init {
        // On init, get the default shows
        for (show in initialShows) {
            dataManager.fetchShow(show)
        }

        showAdapter = ShowAdapter(
            this,
            this
        )

        // subscribe to Datamanager data
        subscribeToData()
    }

    private fun subscribeToData() {
        dataManager.shows.observeForever {
            Log.d(TAG, "It changed $it")
            shows.value = it
        }

        dataManager.episodes.observeForever {
            episodes.value = it
        }
    }

    fun getShowAdapter(): ShowAdapter {
        return showAdapter
    }

    override fun <T: Any> onFragmentInteraction(fragment: String, data: T) {
        /*
        return when (data) {
            is Test -> data.test()
            else -> { // Note the block
        	    print("x is neither 1 nor 2")
    	    }
        }
        */

        when (fragment) {
            "EpisodeAdapter" ->
                handleEpisodeAdapterInteraction(data)
            "ShowAdapter" ->
                handleShowAdapterInteraction(data)
        }
    }

    fun <T: Any> handleEpisodeAdapterInteraction(data: T) {
        Log.d(TAG, "EpisodeAdapter onFragmentInteraction")

        when (data) {
            is Episode -> selectEpisode(data)
            else -> {
                Log.e(TAG, "Incorrect type was passed to you")
            }
        }
    }

    fun <T: Any> handleShowAdapterInteraction(data: T) {
        when (data) {
            is Show -> selectShow(data)
            else -> {
                Log.e(TAG, "Incorrect type was passed to you")
            }
        }
    }

    private fun selectShow(show: Show) {
        selectedShow.value = show
        // Get Episodes for show
    }

    private fun selectEpisode(episode: Episode) {
        selectedEpisode.value = episode
        // Get episode details
    }

    fun showsSize(): Int {
        val showsList = shows.value
        showsList?.let {
            return showsList.size
        }
        return 0
    }

    fun getShowAtPosition(position: Int): Show? {
        val showsList = shows.value

        showsList?.let {
            if (position > showsList.size) {
                return null
            }
            return showsList[position]
        }
        return null
    }

    companion object {
        const val TAG: String = "HomeSharedViewModel"
    }
}