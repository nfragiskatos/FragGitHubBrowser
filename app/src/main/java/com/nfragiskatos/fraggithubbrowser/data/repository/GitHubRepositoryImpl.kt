package com.nfragiskatos.fraggithubbrowser.data.repository

import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.GitHubApi
import com.nfragiskatos.fraggithubbrowser.data.mapper.toUser
import com.nfragiskatos.fraggithubbrowser.data.mapper.toUserSearch
import com.nfragiskatos.fraggithubbrowser.domain.model.User
import com.nfragiskatos.fraggithubbrowser.domain.model.UserSearch
import com.nfragiskatos.fraggithubbrowser.domain.repository.GitHubRepository
import com.nfragiskatos.fraggithubbrowser.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : GitHubRepository {

    override fun searchForUser(searchTerm: String): Flow<Resource<List<UserSearch>>> = flow {
        emit(Resource.Loading(true))
        val results = try {
                api.searchForUser(searchTerm)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.message ?: "Error searching for user"))
            null
            }

        results?.let { userSearchResponseDto ->
        emit(Resource.Success(data = userSearchResponseDto.items.map {
            it.toUserSearch()
        }))

        }
        emit(Resource.Loading(false))
    }

    override fun getUserDetails(username: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading(true))
        val results = try {
            api.getUserDetails(username)
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(e.message ?: "Error getting user details for $username"))
            null
        }

        results?.let { userDto ->
            emit(Resource.Success<User>(userDto.toUser()))
        }
        emit(Resource.Loading(false))
    }
}