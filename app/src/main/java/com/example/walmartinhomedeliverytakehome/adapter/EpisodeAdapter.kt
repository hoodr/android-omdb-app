package com.example.walmartinhomedeliverytakehome.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.walmartinhomedeliverytakehome.R

// Import model and viewModel
import com.example.walmartinhomedeliverytakehome.data.DummyContent.DummyEpisode
import com.example.walmartinhomedeliverytakehome.model.Episode
import com.example.walmartinhomedeliverytakehome.viewmodel.EpisodesViewModel

// Import Generic OnClickListener
import com.example.walmartinhomedeliverytakehome.util.OnFragmentInteractionListener

/**
 * [RecyclerView.Adapter] can display many [Episode] and makes a call to the
 * specified [OnFragmentInteractionListener].
 *
 *
 */
class EpisodeAdapter(
    private val mValues: MutableList<DummyEpisode>?,
    private val mListener: OnFragmentInteractionListener?
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            // Comment out for now
            // val item = v.tag as Episode
            val item = v.tag as DummyEpisode
            mListener?.onFragmentInteraction(TAG, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)


        // inflate(R.layout.fragment_item, parent, false

        return EpisodeHolder(
            layoutInflater.inflate(
                R.layout.fragment_show_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        // TODO: What to display if mValues is null

        mValues?.let {
            val item = mValues[position]

            // TODO: need to define this with new values
            holder.mIdView.text = item.id
            holder.mContentView.text = item.content

            with(holder.mView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }
    }

    override fun getItemCount(): Int = mValues!!.size

    inner class EpisodeHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    companion object {
        const val TAG: String = "EpisodeAdapter"
    }
}