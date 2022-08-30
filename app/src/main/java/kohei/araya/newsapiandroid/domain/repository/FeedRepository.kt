package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.model.Feed
import kotlinx.coroutines.flow.Flow
import java.util.*

interface FeedRepository {

    enum class SearchIn(val item: String) {
        TITLE("title"),
        DESCRIPTION("description"),
        CONTENT("content")
    }

    enum class SortBy(val item: String) {
        RELEVANCY("relevancy"),
        POPULARITY("popularity"),
        PUBLISHED_AT("publishedAt")
    }

    enum class Country(val code: String) {
        JP("jp")
    }

    fun fetchEverything(
        q: String,
        searchInList: List<SearchIn>,
        from: Date,
        to: Date,
        sortBy: SortBy,
        apiKey: String
    ): Flow<List<Feed>>

    fun fetchTopHeadlines(
        country: Country,
        apiKey: String
    ): Flow<List<Feed>>
}