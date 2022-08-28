package kohei.araya.newsapiandroid.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kohei.araya.newsapiandroid.domain.model.Feed
import kohei.araya.newsapiandroid.domain.repository.FeedRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FeedRepository
) : ViewModel() {
    private val _feedList = MutableLiveData<List<Feed>>().apply {
        value = listOf()
    }
    val feedList: LiveData<List<Feed>> = _feedList

    init {
        viewModelScope.launch {
            val feedList = repository.fetchYesterdayGoogleNews()
            _feedList.postValue(feedList)
        }
    }
}