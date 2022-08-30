package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.api.NewsService
import kohei.araya.newsapiandroid.domain.model.Feed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FeedRepositoryClient @Inject constructor(
    private val userService: NewsService
) : FeedRepository {

    override fun fetchEverything(
        q: String,
        searchInList: List<FeedRepository.SearchIn>,
        from: Date,
        to: Date,
        sortBy: FeedRepository.SortBy,
        apiKey: String
    ): Flow<List<Feed>> = flow {
        val searchIn = searchInList.joinToString(",")

        val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
        val fromStr = dataFormat.format(from)
        val toStr = dataFormat.format(to)

        val response = userService.everything(q, searchIn, fromStr, toStr, sortBy.item, apiKey)
        val newsResponse = response.body()
        val list = if (response.isSuccessful && newsResponse != null) {
            Feed.toFeedList(newsResponse)
        } else {
            listOf()
        }
        emit(list)
    }

    override fun fetchTopHeadlines(
        country: FeedRepository.Country,
        apiKey: String
    ): Flow<List<Feed>> = flow {
        val response = userService.topHeadlines(country.code, apiKey)
        val newsResponse = response.body()
        val list = if (response.isSuccessful && newsResponse != null) {
            Feed.toFeedList(newsResponse)
        } else {
            listOf()
        }
        emit(list)
    }
}