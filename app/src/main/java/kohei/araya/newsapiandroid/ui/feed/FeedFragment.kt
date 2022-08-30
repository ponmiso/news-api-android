package kohei.araya.newsapiandroid.ui.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import kohei.araya.newsapiandroid.R

@AndroidEntryPoint
class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = ComposeView(requireContext()).apply {
            setContent {
                MainScreen()
            }
        }
        return root
    }

    @Composable
    fun MainScreen(feedViewModel: FeedViewModel = viewModel()) {
        val yesterdayGoogleFeedList: List<FeedUiModel> by feedViewModel.yesterdayGoogleFeedList.observeAsState(
            emptyList()
        )
        val japanTopHeadLinesFeedList: List<FeedUiModel> by feedViewModel.japanTopHeadLinesFeedList.observeAsState(
            emptyList()
        )
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            YesterdayGoogleNews(feedUiModelList = yesterdayGoogleFeedList)

            Spacer(modifier = Modifier.height(16.dp))

            if (japanTopHeadLinesFeedList.isNotEmpty()) {
                Text(
                    text = "Japan",
                    style = MaterialTheme.typography.h4
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            JapanTopHeadLinesNews(feedUiModelList = japanTopHeadLinesFeedList)
        }
    }

    @Composable
    fun YesterdayGoogleNews(feedUiModelList: List<FeedUiModel>) {
        val width = LocalConfiguration.current.screenWidthDp
        val height = width * 9 / 16

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(feedUiModelList) { feedViewModel ->
                Box(
                    modifier = Modifier
                        .width(width.dp)
                        .clickable { openBrowser(feedViewModel.url) },
                    contentAlignment = Alignment.BottomEnd
                ) {
                    AsyncImage(
                        model = feedViewModel.urlToImage,
                        contentDescription = feedViewModel.title,
                        modifier = Modifier
                            .height(height.dp)
                            .width(width.dp),
                        placeholder = painterResource(R.drawable.no_image),
                        error = painterResource(R.drawable.no_image),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = feedViewModel.title,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }
            }
        }
    }

    @Composable
    fun JapanTopHeadLinesNews(feedUiModelList: List<FeedUiModel>) {
        val width = LocalConfiguration.current.screenWidthDp * 2 / 3
        val height = width * 9 / 16

        LazyRow(
            modifier = Modifier.height((height * 2).dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(feedUiModelList) { feedViewModel ->
                Column(
                    modifier = Modifier
                        .width(width.dp)
                        .clickable { openBrowser(feedViewModel.url) }
                ) {
                    AsyncImage(
                        model = feedViewModel.urlToImage,
                        contentDescription = feedViewModel.title,
                        modifier = Modifier
                            .height(height.dp)
                            .width(width.dp),
                        placeholder = painterResource(R.drawable.no_image),
                        error = painterResource(R.drawable.no_image),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = feedViewModel.title,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }
            }
        }
    }

    private fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}