package com.nfragiskatos.fraggithubbrowser.data.data_source.remote

import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.GitHubApi
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.dto.UserSearchResponseDto
import com.nfragiskatos.fraggithubbrowser.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
//@Suppress("IllegalIdentifier")
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
    fun testSetup() {
        assertThat(gitHubApi).isNotNull()
    }

    @Test
    fun testApiUserSearch() = runBlocking {
        val searchForUser: UserSearchResponseDto = gitHubApi.searchForUser("brandon")

        assertThat(searchForUser).isNotNull()
        assertThat(searchForUser.items).isNotEmpty()
    }

}