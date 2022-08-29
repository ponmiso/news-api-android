package kohei.araya.newsapiandroid.domain.model

data class NewsEverythingResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsEverything>
)
