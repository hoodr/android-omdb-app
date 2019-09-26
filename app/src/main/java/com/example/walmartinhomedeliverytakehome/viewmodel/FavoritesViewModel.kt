package com.example.walmartinhomedeliverytakehome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.walmartinhomedeliverytakehome.data.DummyContent
import com.example.walmartinhomedeliverytakehome.data.DummyContent.DummyEpisode

class FavoritesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    // Dummy stuff
    val episodes: MutableList<DummyEpisode> = DummyContent.EPISODE_ITEMS
}