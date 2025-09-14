package com.furkan.mortymap.presentation.origins

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.furkan.mortymap.data.model.Origin

@Composable
fun LocationItem(
    origin: Origin,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(
                text = origin.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        supportingContent = {
            val subtitleParts = buildList {
                if (origin.type.isNotBlank()) add(origin.type)
                if (origin.dimension.isNotBlank()) add(origin.dimension)
                add("${origin.residents.size} " + if (origin.residents.size == 1) "resident" else "residents")
            }
            Text(
                text = subtitleParts.joinToString(" • "),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        leadingContent = { MonogramCircle(origin.name) },
        modifier = modifier
            .heightIn(min = 88.dp)
            .padding(vertical = 6.dp),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    )
    Divider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    )
}

@Composable
private fun MonogramCircle(text: String, sizeDp: Int = 56) {
    val initial = text.trim().firstOrNull()?.uppercaseChar() ?: 'L'
    Box(
        modifier = Modifier
            .size(sizeDp.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}