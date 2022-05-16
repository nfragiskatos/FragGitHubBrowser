package com.nfragiskatos.fraggithubbrowser.data.mapper

import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.UserDto
import com.nfragiskatos.fraggithubbrowser.domain.model.User

fun UserDto.toUser(): User {
    return User(
        avatarUrl = avatarUrl,
        createdAt = createdAt,
        email = email,
        htmlUrl = htmlUrl,
        id = id,
        login = login,
        name = name,
        organizationsUrl = organizationsUrl,
        publicGists = publicGists,
        publicRepos = publicRepos,
        reposUrl = reposUrl,
        type = type,
        url = url
    )
}