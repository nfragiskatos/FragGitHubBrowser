package com.nfragiskatos.fraggithubbrowser.domain.repository

import com.google.common.truth.Truth.assertThat
import com.nfragiskatos.fraggithubbrowser.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
class GitHubRepositoryTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repo: GitHubRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testSetUp() {
        assertThat(repo)
            .isNotNull()
    }

    @Test
    fun searchForUser() = runBlocking {
        repo.searchForUser("steve")
            .collect { results ->
                assertThat(results).isNotNull()
                assertThat(results).isNotEmpty()
            }
    }
}