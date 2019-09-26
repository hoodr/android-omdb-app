package com.example.walmartinhomedeliverytakehome.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.adapters.ImageViewBindingAdapter
import kotlinx.android.synthetic.main.fragment_show_card.view.*

import com.example.walmartinhomedeliverytakehome.R
import com.example.walmartinhomedeliverytakehome.model.Show
import com.example.walmartinhomedeliverytakehome.util.OnFragmentInteractionListener
import com.example.walmartinhomedeliverytakehome.viewmodel.HomeSharedViewModel

/**
 * [RecyclerView.Adapter] can display many [Show] and makes a call to the
 * specified [OnFragmentInteractionListener].
 *
 *
 */
class ShowAdapter(
    private val mViewModel: HomeSharedViewModel,
    private val mListener: OnFragmentInteractionListener?
) : RecyclerView.Adapter<ShowAdapter.ShowHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Show
            mListener?.onFragmentInteraction(TAG, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)

        return ShowHolder(
            layoutInflater.inflate(
                R.layout.fragment_show_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShowHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return mViewModel.showsSize()
    }

    inner class ShowHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mImageView: ImageView = mView.show_image
        val mShowView: TextView = mView.show_name

        fun bind(position: Int) {
            val item = mViewModel.getShowAtPosition(position)

            item?.let {
                bindImage(null)
                bindName(item.Title)

                with(this.mView) {
                    tag = item
                    setOnClickListener(mOnClickListener)
                }
            }
        }

        // TODO: Not sure why this is
        @SuppressLint("RestrictedApi")
        private fun bindImage(imageUri: String?) {
            ImageViewBindingAdapter.setImageUri(mImageView, imageUri)
        }

        private fun bindName(name: String) {
            mShowView.text = name
        }
    }

    companion object {
        const val TAG: String = "ShowAdapter"
    }
}