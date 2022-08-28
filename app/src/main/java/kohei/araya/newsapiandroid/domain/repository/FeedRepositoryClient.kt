package kohei.araya.newsapiandroid.domain.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kohei.araya.newsapiandroid.domain.api.NewsService
import kohei.araya.newsapiandroid.domain.model.Feed
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class FeedRepositoryClient @Inject constructor() : FeedRepository {
    override suspend fun fetch(
        q: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        apiKey: String
    ): List<Feed> {
        val client = OkHttpClient.Builder()
            .build()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val userService = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(NewsService::class.java)

        val response = userService.everything(q, searchIn, from, to, sortBy, apiKey)

        val newsResponse = response.body()
        return if (response.isSuccessful && newsResponse != null) {
            Feed.toFeedList(newsResponse)
        } else {
            listOf()
        }
    }
}