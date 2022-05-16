package com.nfragiskatos.fraggithubbrowser.domain.model

import java.util.*

data class User(
    val avatarUrl: String?,
    val createdAt: Date,
    val email: String?,
    val htmlUrl: String?,
    val id: Int,
    val login: String,
    val name: String,
    val organizationsUrl: String?,
    val publicGists: Int,
    val publicRepos: Int,
    val reposUrl: String,
    val type: String,
    val url: String?
)
