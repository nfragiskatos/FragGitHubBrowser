package com.nfragiskatos.fraggithubbrowser.domain.repository

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.nfragiskatos.fraggithubbrowser.di.AppModule
import com.nfragiskatos.fraggithubbrowser.domain.model.UserSearch
import com.nfragiskatos.fraggithubbrowser.util.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
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
    fun searchForUser_expect3FlowValues() = runTest {
        val count = repo.searchForUser("andy")
            .count()
        assertThat(count).isEqualTo(3)
    }

    @Test
    fun searchForUser_expectCorrectFlowEmissions() = runTest {
        val emissions = repo.searchForUser("andy")
            .toList()
        assertThat(emissions).hasSize(3)
        assertThat(emissions[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(emissions[1]).isInstanceOf(Resource.Success::class.java)
        assertThat(emissions[2]).isInstanceOf(Resource.Loading::class.java)
    }
}