package com.nfragiskatos.fraggithubbrowser.data.date_source.remote

import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.UserSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/users")
    suspend fun searchForUser(@Query("q") searchTerm: String): UserSearchResponseDto
}