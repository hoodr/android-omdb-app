package com.example.walmartinhomedeliverytakehome.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil

import com.example.walmartinhomedeliverytakehome.R
import com.example.walmartinhomedeliverytakehome.databinding.FragmentEpisodesBinding
import com.example.walmartinhomedeliverytakehome.util.OnFragmentInteractionListener
import com.example.walmartinhomedeliverytakehome.viewmodel.HomeSharedViewModel

/**
 * A fragment representing a list of Episodes.
 *
 * Uses:
 *  - List of episodes for a show
 *  - List of favorited episodes
 *
 * Activities containing this fragment MUST implement the
 * EpisodeListViewFragment.OnListFragmentInteractionListener interface.
 */
class EpisodeListViewFragment : Fragment() {
    private val columns = 2
    private lateinit var homeSharedViewModel: HomeSharedViewModel

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeSharedViewModel = activity?.run {
            ViewModelProviders.of(this)[HomeSharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEpisodesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shows,
            container,
            false
        )

        val view =
            inflater.inflate(R.layout.fragment_episodes, container, false)

        val rootView : View = binding.root
        val recyclerView : RecyclerView = rootView.findViewById(R.id.episode_fragment_recycler_view)

        with(view) {
            recyclerView.layoutManager = GridLayoutManager(context, columns)
        }

        binding.model = homeSharedViewModel

        return recyclerView
    }

    companion object {
        const val TAG: String = "ShowsFragment"


        @JvmStatic
        fun newInstance(columnCount: Int) = EpisodeListViewFragment()
    }
}
