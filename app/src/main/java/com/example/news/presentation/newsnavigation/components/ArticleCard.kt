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
import com.example.news.data.model.SavedArticle
import com.example.news.util.Util


@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: SavedArticle, navigateToDetailScreen: (article: SavedArticle)->Unit) {
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth(), onClick = { navigateToDetailScreen(article) }, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
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
                    source = article.sourceName,
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
        article = Util.article,
        navigateToDetailScreen = {}
    )
}