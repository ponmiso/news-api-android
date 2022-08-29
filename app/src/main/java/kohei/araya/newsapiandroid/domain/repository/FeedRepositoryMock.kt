package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.model.Feed
import kohei.araya.newsapiandroid.domain.model.Source
import java.util.*
import javax.inject.Inject

class FeedRepositoryMock @Inject constructor() : FeedRepository {
    override suspend fun fetchEverything(
        q: String,
        searchInList: List<FeedRepository.SearchIn>,
        from: Date,
        to: Date,
        sortBy: FeedRepository.SortBy,
        apiKey: String
    ): List<Feed> {
        val feed1 = Feed(
            Source("feed1", "Feed1"),
            "feed1_author",
            "feed1_title",
            "feed1_description",
            "feed1_url",
            "feed1_urlToImage",
            "feed1_publishedAt",
            "feed1_content"
        )
        val feed2 = Feed(
            Source("feed2", "Feed2"),
            "feed2_author",
            "feed3_title",
            "feed2_description",
            "feed2_url",
            "feed2_urlToImage",
            "feed2_publishedAt",
            "feed2_content"
        )
        val feed3 = Feed(
            Source("feed3", "Feed3"),
            "feed3_author",
            "feed3_title",
            "feed3_description",
            "feed3_url",
            "feed3_urlToImage",
            "feed3_publishedAt",
            "feed3_content"
        )
        return listOf(feed1, feed2, feed3)
    }

    override suspend fun fetchTopHeadlines(
        country: FeedRepository.Country,
        apiKey: String
    ): List<Feed> {
        val feed4 = Feed(
            Source("feed4", "Feed4"),
            "feed4_author",
            "feed4_title",
            "feed4_description",
            "feed4_url",
            "feed4_urlToImage",
            "feed4_publishedAt",
            "feed4_content"
        )
        return listOf(feed4)
    }
}