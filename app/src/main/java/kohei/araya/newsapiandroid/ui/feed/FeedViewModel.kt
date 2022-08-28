package kohei.araya.newsapiandroid.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kohei.araya.newsapiandroid.BuildConfig
import kohei.araya.newsapiandroid.domain.repository.FeedRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FeedRepository
) : ViewModel() {
    private val _feedUiModel = MutableLiveData<List<FeedUiModel>>().apply {
        value = listOf()
    }
    val feedUiModel: LiveData<List<FeedUiModel>> = _feedUiModel

    private val yesterday: String by lazy {
        val now = Date()
        val calendar = Calendar.getInstance()
        calendar.time = now
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val yesterdayDate = calendar.time
        val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
        return@lazy dataFormat.format(yesterdayDate)
    }

    init {
        viewModelScope.launch {
            val feedList = repository.fetch(
                "google",
                "title",
                yesterday,
                yesterday,
                "publishedAt",
                BuildConfig.NEWS_API_KEY
            )
            val uiModelList = FeedUiModel.toFeedUiModelList(feedList)
            _feedUiModel.postValue(uiModelList)
        }
    }
}