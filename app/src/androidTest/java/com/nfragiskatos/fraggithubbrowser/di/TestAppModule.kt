package com.nfragiskatos.fraggithubbrowser.di

import com.nfragiskatos.fraggithubbrowser.BuildConfig
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.GitHubApi
import com.nfragiskatos.fraggithubbrowser.data.repository.GitHubRepositoryImpl
import com.nfragiskatos.fraggithubbrowser.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun providesGitHubApi(): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provesGitHubRepository(api: GitHubApi) : GitHubRepository {
        return GitHubRepositoryImpl(api)
    }
}