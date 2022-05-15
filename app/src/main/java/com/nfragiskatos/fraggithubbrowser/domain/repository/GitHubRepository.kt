package com.nfragiskatos.fraggithubbrowser.domain.repository

import com.nfragiskatos.fraggithubbrowser.domain.model.UserSearch
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun searchForUser(searchTerm: String) : Flow<List<UserSearch>>
}