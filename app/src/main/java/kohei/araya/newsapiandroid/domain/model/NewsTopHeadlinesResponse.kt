package kohei.araya.newsapiandroid.domain.model

data class NewsTopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsEverything>
)
