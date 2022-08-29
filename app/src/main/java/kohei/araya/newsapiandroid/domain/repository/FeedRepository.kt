package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.model.Feed

interface FeedRepository {
    suspend fun fetchEverything(
        q: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        apiKey: String
    ): List<Feed>

    suspend fun fetchTopHeadlines(
        country: String,
        apiKey: String
    ): List<Feed>
}