package com.example.walmartinhomedeliverytakehome.model

/**
 * A Model Class representing a show
 *
 * A Show has many Episodes
 *
 * TODO: this is empty for now
 *
 *
 * "Title": "The Office",
 * "Year": "2005–2013",
 * "Rated": "TV-PG",
 * "Released": "24 Mar 2005",
 * "Runtime": "22 min",
 * "Genre": "Comedy",
 * "Director": "N/A",
 * "Writer": "Greg Daniels, Ricky Gervais, Stephen Merchant",
 * "Actors": "Rainn Wilson, John Krasinski, Jenna Fischer, Leslie David Baker",
 * "Plot": "A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.",
 * "Language": "English",
 * "Country": "USA",
 * "Awards": "Won 1 Golden Globe. Another 45 wins & 186 nominations.",
 * "Poster": "https://m.media-amazon.com/images/M/MV5BMTgzNjAzMDE0NF5BMl5BanBnXkFtZTcwNTEyMzM3OA@@._V1_SX300.jpg",
 * "Ratings": [
 *     {
 *         "Source": "Internet Movie Database",
 *         "Value": "8.8/10"
 *     }
 * ],
 * "Metascore": "N/A",
 * "imdbRating": "8.8",
 * "imdbVotes": "309,909",
 * "imdbID": "tt0386676",
 * "Type": "series",
 * "totalSeasons": "9",
 * "Response": "True"
 */
class Show(
    val Title: String,
    val Writer: String,
    val Plot: String,
    val Poster: String,
    val imdbID: String,
    val Actors: String
) {
    override fun toString(): String {
        return "$Title $Writer $Plot $Poster $imdbID $Actors"
    }
}