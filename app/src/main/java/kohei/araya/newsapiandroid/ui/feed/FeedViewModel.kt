package kohei.araya.newsapiandroid.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kohei.araya.newsapiandroid.BuildConfig
import kohei.araya.newsapiandroid.domain.repository.FeedRepository
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FeedRepository
) : ViewModel() {
    private val _yesterdayGoogleFeedList = MutableLiveData<List<FeedUiModel>>().apply {
        value = listOf()
    }
    val yesterdayGoogleFeedList: LiveData<List<FeedUiModel>> = _yesterdayGoogleFeedList

    private val _japanTopHeadLinesFeedList = MutableLiveData<List<FeedUiModel>>().apply {
        value = listOf()
    }
    val japanTopHeadLinesFeedList: LiveData<List<FeedUiModel>> = _japanTopHeadLinesFeedList

    private val yesterday: Date by lazy {
        val now = Date()
        val calendar = Calendar.getInstance()
        calendar.time = now
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        return@lazy calendar.time
    }

    init {
        viewModelScope.launch {
            // 昨日Googleに言及している記事を取得
            val everythingList = repository.fetchEverything(
                "google",
                listOf(FeedRepository.SearchIn.TITLE),
                yesterday,
                yesterday,
                FeedRepository.SortBy.PUBLISHED_AT,
                BuildConfig.NEWS_API_KEY
            )
            val yesterdayGoogleFeedList = FeedUiModel.toFeedUiModelList(everythingList)
            _yesterdayGoogleFeedList.postValue(yesterdayGoogleFeedList)

            // 日本の最新ヘッドラインを取得
            val headLiveList = repository.fetchTopHeadlines(
                FeedRepository.Country.JP,
                BuildConfig.NEWS_API_KEY
            )
            val japanTopHeadLinesFeedList = FeedUiModel.toFeedUiModelList(headLiveList)
            _japanTopHeadLinesFeedList.postValue(japanTopHeadLinesFeedList)
        }
    }
}