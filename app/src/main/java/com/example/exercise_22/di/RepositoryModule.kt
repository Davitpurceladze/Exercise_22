package com.example.exercise_22.di

import com.example.exercise_22.data.common.HandleResponse
import com.example.exercise_22.data.repository.post.PostsRepositoryImpl
import com.example.exercise_22.data.repository.story.StoriesRepositoryImpl
import com.example.exercise_22.data.service.posts.PostsService
import com.example.exercise_22.data.service.stories.StoriesService
import com.example.exercise_22.domain.repository.posts.PostsRepository
import com.example.exercise_22.domain.repository.stories.StoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStoriesRepository(
        storiesService: StoriesService,
        handleResponse: HandleResponse
    ): StoriesRepository {
        return StoriesRepositoryImpl(
            storiesService = storiesService,
            handleResponse = handleResponse
        )
    }

    @Provides
    @Singleton
    fun providePostsRepository(
        postsService: PostsService,
        handleResponse: HandleResponse
    ): PostsRepository {
        return PostsRepositoryImpl(
            postsService = postsService,
            handleResponse = handleResponse
        )
    }
}