package com.example.walmartinhomedeliverytakehome.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.walmartinhomedeliverytakehome.R
import com.example.walmartinhomedeliverytakehome.viewmodel.HomeSharedViewModel

/**
 * A fragment representing a list of Favorite Episodes.
 *
 * Uses:
 *  - List of episodes for a show
 *  - List of favorited episodes
 *
 */
class FavoritesFragment : Fragment() {
    private lateinit var homeSharedViewModel: HomeSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeSharedViewModel= activity?.run {
            ViewModelProviders.of(this)[HomeSharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_episodes, container, false)

        // TODO: bind adapter to view

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
            }
        }
        return view
    }

    companion object {
        const val TAG: String = "FavoritesFragment"

        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}