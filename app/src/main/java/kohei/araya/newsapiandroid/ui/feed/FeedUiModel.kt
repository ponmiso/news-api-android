package kohei.araya.newsapiandroid.ui.feed

import kohei.araya.newsapiandroid.domain.model.Feed

data class FeedUiModel(
    val title: String?,
    val url: String?,
    val urlToImage: String?
) {
    companion object {
        fun toFeedUiModelList(feedList: List<Feed>): List<FeedUiModel> =
            feedList.map {
                FeedUiModel(
                    it.title,
                    it.url,
                    it.urlToImage
                )
            }
    }
}
