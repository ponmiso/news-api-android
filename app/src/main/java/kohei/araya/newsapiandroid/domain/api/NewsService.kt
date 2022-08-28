package kohei.araya.newsapiandroid.domain.api

import kohei.araya.newsapiandroid.domain.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {
    @GET("v2/everything")
    suspend fun everything(
        @Query("q") q: String,
        @Query("searchIn") searchIn: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}