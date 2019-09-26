package com.example.walmartinhomedeliverytakehome.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.walmartinhomedeliverytakehome.R
import com.example.walmartinhomedeliverytakehome.model.Episode
import com.example.walmartinhomedeliverytakehome.viewmodel.HomeSharedViewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class EpisodeDetailFragment : Fragment() {
    private lateinit var episode: Episode
    private lateinit var homeSharedViewModel: HomeSharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeSharedViewModel = activity?.run {
            ViewModelProviders.of(this)[HomeSharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        // Set episode from view model
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_detail, container, false)
    }

    companion object {
        const val TAG: String = "EpisodeDetailFragment"

        @JvmStatic
        fun newInstance() = EpisodeDetailFragment()
    }
}
