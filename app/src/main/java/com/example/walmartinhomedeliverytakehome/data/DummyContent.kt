package com.example.walmartinhomedeliverytakehome.data

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val SHOW_ITEMS: MutableList<DummyShow> = ArrayList()
    val EPISODE_ITEMS: MutableList<DummyEpisode> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val SHOW_ITEM_MAP: MutableMap<String, DummyShow> = HashMap()
    val EPISODE_ITEM_MAP: MutableMap<String, DummyEpisode> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItems(
                createDummyItems(i)
            )
        }
    }

    private fun addItems(item: Pair<DummyShow,DummyEpisode>) {
        val (item1, item2) = item

        SHOW_ITEMS.add(item1)
        EPISODE_ITEMS.add(item2)
        SHOW_ITEM_MAP.put(item1.id, item1)
        EPISODE_ITEM_MAP.put(item2.id, item2)
    }

    private fun createDummyItems(position: Int): Pair<DummyShow, DummyEpisode> {
        val a = DummyShow(
            position.toString(),
            "Item " + position,
            makeDetails(position)
        )

        val b = DummyEpisode(
            position.toString(),
            "Item " + position,
            makeDetails(position)
        )

        return Pair(a, b)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */

    class DummyShow(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyEpisode(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
