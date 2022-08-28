package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.api.NewsService
import kohei.araya.newsapiandroid.domain.model.Feed
import javax.inject.Inject

class FeedRepositoryClient @Inject constructor(
    private val userService: NewsService
) : FeedRepository {
    override suspend fun fetch(
        q: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        apiKey: String
    ): List<Feed> {
        val response = userService.everything(q, searchIn, from, to, sortBy, apiKey)
        val newsResponse = response.body()
        return if (response.isSuccessful && newsResponse != null) {
            Feed.toFeedList(newsResponse)
        } else {
            listOf()
        }
    }
}