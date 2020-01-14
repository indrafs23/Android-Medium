package com.example.moviecatalogue2.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
        const val API_KEY = "5b83a6ddb00520ba4a54002596985a7d"
        const val LANGUAGE = "en-US"
    }

    @GET("/3/discover/movie")
    fun getMovies(
        @Query("api_key") key: String,
        @Query("language") key2: String
    ): Call<ListFilm>

    @GET(value = "/3/discover/tv")
    fun getTvShow(
        @Query("api_key") key: String,
        @Query("language") key2: String
    ): Call<ListTvShow>
}