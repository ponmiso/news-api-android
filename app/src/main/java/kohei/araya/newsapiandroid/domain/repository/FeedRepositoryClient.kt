package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.api.NewsService
import kohei.araya.newsapiandroid.domain.model.Feed
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FeedRepositoryClient @Inject constructor(
    private val userService: NewsService
) : FeedRepository {
    override suspend fun fetchEverything(
        q: String,
        searchInList: List<FeedRepository.SearchIn>,
        from: Date,
        to: Date,
        sortBy: FeedRepository.SortBy,
        apiKey: String
    ): List<Feed> {
        val searchIn = searchInList.joinToString(",")

        val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
        val fromStr = dataFormat.format(from)
        val toStr = dataFormat.format(to)

        val response = userService.everything(q, searchIn, fromStr, toStr, sortBy.item, apiKey)
        val newsResponse = response.body()
        return if (response.isSuccessful && newsResponse != null) {
            Feed.toFeedList(newsResponse)
        } else {
            listOf()
        }
    }

    override suspend fun fetchTopHeadlines(
        country: FeedRepository.Country,
        apiKey: String
    ): List<Feed> {
        val response = userService.topHeadlines(country.code, apiKey)
        val newsResponse = response.body()
        return if (response.isSuccessful && newsResponse != null) {
            Feed.toFeedList(newsResponse)
        } else {
            listOf()
        }
    }
}