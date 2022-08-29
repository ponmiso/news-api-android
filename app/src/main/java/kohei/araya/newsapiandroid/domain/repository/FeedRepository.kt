package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.model.Feed
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

    suspend fun fetchEverything(
        q: String,
        searchInList: List<SearchIn>,
        from: Date,
        to: Date,
        sortBy: SortBy,
        apiKey: String
    ): List<Feed>

    suspend fun fetchTopHeadlines(
        country: Country,
        apiKey: String
    ): List<Feed>
}