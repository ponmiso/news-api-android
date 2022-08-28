package kohei.araya.newsapiandroid.domain.model

data class Feed(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) {
    companion object {
        fun toFeedList(newsResponse: NewsResponse): List<Feed> =
            newsResponse.articles.map {
                Feed(
                    it.source,
                    it.author,
                    it.title,
                    it.description,
                    it.url,
                    it.urlToImage,
                    it.publishedAt,
                    it.content
                )
            }
    }
}