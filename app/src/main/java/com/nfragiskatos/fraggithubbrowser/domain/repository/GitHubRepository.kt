package com.nfragiskatos.fraggithubbrowser.domain.repository

import com.nfragiskatos.fraggithubbrowser.domain.model.User
import com.nfragiskatos.fraggithubbrowser.domain.model.UserSearch
import com.nfragiskatos.fraggithubbrowser.util.Resource
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun searchForUser(searchTerm: String) : Flow<Resource<List<UserSearch>>>

    fun getUserDetails(username: String) : Flow<Resource<User>>
}