package com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto

import com.google.gson.annotations.SerializedName

data class UserSearchResponseDto(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_result") val incompleteResult: Boolean,
    @SerializedName("items") val items: List<UserSearchDto>
)
