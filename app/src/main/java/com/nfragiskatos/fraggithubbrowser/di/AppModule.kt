package com.nfragiskatos.fraggithubbrowser.di

import com.nfragiskatos.fraggithubbrowser.BuildConfig
import com.nfragiskatos.fraggithubbrowser.data.date_source.remote.GitHubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGitHubApi(): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }
}