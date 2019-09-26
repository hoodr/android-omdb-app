package com.example.walmartinhomedeliverytakehome.util

/**
 * This interface must be implemented by anything that wishes
 * to use onFragmentInteraction method
 *
 */
interface OnFragmentInteractionListener {
//    fun <T> onFragmentInteraction(fragment: String, vararg data: Pair<String,T>)
    fun <T: Any>  onFragmentInteraction(fragment: String, data: T)
}