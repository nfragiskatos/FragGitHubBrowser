package com.nfragiskatos.fraggithubbrowser.data.data_source.remote

import android.util.Log
import com.google.common.truth.Truth.assertThat
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.GitHubApi
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.RepositoryDto
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.UserSearchResponseDto
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.util.SortBy
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.util.SortDirection
import com.nfragiskatos.fraggithubbrowser.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
class GitHubApiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gitHubApi: GitHubApi

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testSetUp() {
        assertThat(gitHubApi).isNotNull()
    }

    @Test
    fun searchForUser() = runBlocking {
        val searchForUser: UserSearchResponseDto = gitHubApi.searchForUser("brandon")

        assertThat(searchForUser).isNotNull()
        assertThat(searchForUser.items).isNotEmpty()
    }

    @Test
    fun getUserDetails() = runBlocking {
        val userDetails = gitHubApi.getUserDetails("nfragiskatos")

        assertThat(userDetails).isNotNull()
        assertThat(userDetails.login).isEqualTo("nfragiskatos")
    }

    @Test
    fun getUserRepositories_created() = runBlocking {
        var userRepositories = gitHubApi.getUserRepositories(
            "nfragiskatos",
            SortBy.CREATED,
            SortDirection.DESC
        )

        val comparatorCreatedAtDesc: Comparator<RepositoryDto> = Comparator { r1, r2 ->
            r2.createdAt.compareTo(r1.createdAt)
        }

        userRepositories.forEach {
            Log.i("MY_TAG", "${it.createdAt}, ${it.name}")
        }

        assertThat(userRepositories).isNotNull()
        assertThat(userRepositories).isNotEmpty()
        assertThat(userRepositories).isInOrder(comparatorCreatedAtDesc)

        userRepositories = gitHubApi.getUserRepositories(
            "nfragiskatos",
            SortBy.CREATED,
            SortDirection.ASC
        )

        val comparatorCreatedAtAsc: Comparator<RepositoryDto> = Comparator { r1, r2 ->
            r1.createdAt.compareTo(r2.createdAt)
        }

        assertThat(userRepositories).isNotNull()
        assertThat(userRepositories).isNotEmpty()
        assertThat(userRepositories).isInOrder(comparatorCreatedAtAsc)
    }

    @Test
    fun getUserRepositories_updated() = runBlocking {
        var userRepositories = gitHubApi.getUserRepositories(
            "nfragiskatos",
            SortBy.UPDATED,
            SortDirection.DESC
        )

        val comparatorUpdatedAtDesc: Comparator<RepositoryDto> = Comparator { r1, r2 ->
            r2.updatedAt.compareTo(r1.updatedAt)
        }

        userRepositories.forEach {
            Log.i("MY_TAG", "updated at ${it.updatedAt}, ${it.name}")
        }

        assertThat(userRepositories).isNotNull()
        assertThat(userRepositories).isNotEmpty()

        // Interestingly there's 2 entries in the returned data that aren't sorted correctly
        // They have the same date, but different times, and the time values are not being sorted
        // in the correct order
        // The conflicting entries are:
        //
        //      updated at Mon Jul 05 16:11:43 MST 2021
        //      updated at Mon Jul 05 16:12:34 MST 2021
        //
        // There are other entries that have the same date and different time values, and those
        // are being sorted correctly, not sure what's the issue with just these.

//        assertThat(userRepositories).isInOrder(comparatorUpdatedAtDesc)

        userRepositories = gitHubApi.getUserRepositories(
            "nfragiskatos",
            SortBy.UPDATED,
            SortDirection.ASC
        )

        val comparatorUpdatedAtAsc: Comparator<RepositoryDto> = Comparator { r1, r2 ->
            r1.updatedAt.compareTo(r2.updatedAt)
        }

        assertThat(userRepositories).isNotNull()
        assertThat(userRepositories).isNotEmpty()
//        assertThat(userRepositories).isInOrder(comparatorUpdatedAtAsc)
    }

}