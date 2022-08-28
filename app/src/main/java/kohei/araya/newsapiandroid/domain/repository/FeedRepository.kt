package kohei.araya.newsapiandroid.domain.repository

import kohei.araya.newsapiandroid.domain.model.Feed

interface FeedRepository {
    suspend fun fetchYesterdayGoogleNews(): List<Feed>
}