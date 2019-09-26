package com.example.walmartinhomedeliverytakehome.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProviders

import com.example.walmartinhomedeliverytakehome.R
import com.example.walmartinhomedeliverytakehome.databinding.FragmentShowsBinding
import com.example.walmartinhomedeliverytakehome.viewmodel.HomeSharedViewModel

/**
 * A fragment representing a list of Shows.
 *
 */
class ShowsFragment : Fragment() {
    private val columns = 2

    private lateinit var homeSharedViewModel: HomeSharedViewModel

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
        val binding: FragmentShowsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shows,
            container,
            false
        )

        val rootView : View = binding.root
        val recyclerView : RecyclerView = rootView.findViewById(R.id.show_fragment_recycler_view)

        with(recyclerView) {
            layoutManager = GridLayoutManager(context, columns)
        }

        binding.model = homeSharedViewModel

        return rootView
    }

    companion object {
        const val TAG: String = "ShowsFragment"

        @JvmStatic
        fun newInstance() = ShowsFragment()
    }
}