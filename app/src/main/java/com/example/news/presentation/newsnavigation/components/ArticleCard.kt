package com.example.news.presentation.newsnavigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.news.data.model.Article
import com.example.news.data.model.Source
import com.example.news.util.Util


@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: Article, navigateToDetailScreen: (url: String)->Unit) {
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth(), onClick = { navigateToDetailScreen(article.url) }, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(intrinsicSize = IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                DetailsText(
                    modifier = Modifier.weight(0.6f),
                    source = article.source.name,
                    title = article.title,
                    description = article.description
                )
                AsyncImage(
                    modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)).weight(0.4f),
                    model = article.urlToImage,
                    contentDescription = article.title,
                    contentScale = ContentScale.FillBounds
                )
            }
            PublishAtText(publishedAt = article.publishedAt, contentLength = article.content.length)
        }
    }
}

@Composable
fun DetailsText(modifier: Modifier = Modifier, source: String, title: String, description: String) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = source, style = MaterialTheme.typography.titleSmall)
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = description, maxLines = 3, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun PublishAtText(modifier: Modifier = Modifier, publishedAt: String, contentLength: Int) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = Util.formatDate(publishedAt), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Box(
            modifier = modifier
                .clip(shape = CircleShape)
                .background(color = Color.Gray)
                .size(4.dp)
        )
        Text(text = Util.readingTimeEstimator(contentLength), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }
}

@Preview
@Composable
private fun PreviewArticleCard() {
    ArticleCard(
        article = Article(
            source = Source(id = "null", "NDTV News"),
            author = "NDTV Sports Desk",
            title = "India vs Zimbabwe Live Score, 2nd T20I: After Abhishek Sharma's Historic Ton, India Close In On Win - NDTV Sports",
            description = "India vs Zimbabwe 2nd T20I Highlights: Abhishek Sharma's 100 off 47 helped India register a 100-run victory over Zimbabwe on Sunday.",
            url = "https://sports.ndtv.com/cricket/india-vs-zimbabwe-live-score-2nd-t20-match-7-july-ind-vs-zim-latest-scorecard-shubman-gill-sikandar-raza-6052501",
            urlToImage = "https://c.ndtvimg.com/2024-07/uu2u73kg_india-bcci-x_625x300_07_July_24.jpg?im=FaceCrop,algorithm=dnn,width=1200,height=738",
            publishedAt = "2024-07-07T14:09:34Z",
            content = "India vs Zimbabwe 2nd T20I Highlights: Abhishek Sharma starred with a historic century in India's dominating 100-run win over Zimbabwe in the second T20I in Harare on Sunday. Abhishek scored 100 off … [+991 chars]"
        )
        ,
        navigateToDetailScreen = {}
    )
}