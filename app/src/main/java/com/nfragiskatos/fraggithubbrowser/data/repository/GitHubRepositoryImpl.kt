package com.nfragiskatos.fraggithubbrowser.data.repository

import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.GitHubApi
import com.nfragiskatos.fraggithubbrowser.data.mapper.toUserSearch
import com.nfragiskatos.fraggithubbrowser.domain.model.UserSearch
import com.nfragiskatos.fraggithubbrowser.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : GitHubRepository {

    override fun searchForUser(searchTerm: String): Flow<List<UserSearch>> = flow {
        val results = api.searchForUser(searchTerm).items.map {
            it.toUserSearch()
        }

        emit(results)
    }
}