package kohei.araya.newsapiandroid.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kohei.araya.newsapiandroid.domain.repository.FeedRepository
import kohei.araya.newsapiandroid.domain.repository.FeedRepositoryMock

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindFeedRepository(client: FeedRepositoryMock): FeedRepository
}