package com.nfragiskatos.fraggithubbrowser.presentation.usersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfragiskatos.fraggithubbrowser.domain.model.UserSearch
import com.nfragiskatos.fraggithubbrowser.domain.repository.GitHubRepository
import com.nfragiskatos.fraggithubbrowser.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val repo: GitHubRepository
) : ViewModel() {

    private val _user = MutableStateFlow(UserSearch(0, "init state"))
    val user : MutableStateFlow<UserSearch> = _user

    fun searchUser() {
        viewModelScope.launch {
            val results = repo.searchForUser("steve")

            results.collect {
                when (it) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> TODO()
                    is Resource.Success -> _user.value = it.data?.random() ?: UserSearch(0, "empty")
                }
            }
        }
    }
}