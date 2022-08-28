package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.model.Feed

interface FeedRepository {
    suspend fun fetch(
        q: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        apiKey: String
    ): List<Feed>
}