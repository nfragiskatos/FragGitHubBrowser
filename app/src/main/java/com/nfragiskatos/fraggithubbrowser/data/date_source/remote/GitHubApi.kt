package com.nfragiskatos.fraggithubbrowser.data.date_source.remote

import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.RepositoryDto
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.UserDto
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.UserSearchResponseDto
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.util.SortBy
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.util.SortDirection
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/users")
    suspend fun searchForUser(@Query("q") searchTerm: String): UserSearchResponseDto

    @GET("/users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): UserDto

    @GET("/users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String,
        @Query("sort") sortBy: SortBy,
        @Query("direction") sortDirection: SortDirection
    ): List<RepositoryDto>
}