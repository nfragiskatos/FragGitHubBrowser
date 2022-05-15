package com.nfragiskatos.fraggithubbrowser.data.mapper

import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.UserSearchDto
import com.nfragiskatos.fraggithubbrowser.domain.model.UserSearch

fun UserSearchDto.toUserSearch(): UserSearch {
    return UserSearch(
        id = id,
        login = login
    )
}