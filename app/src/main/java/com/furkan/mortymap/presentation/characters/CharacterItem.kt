package com.furkan.mortymap.presentation.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.furkan.mortymap.data.model.Character

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = { Text(text = character.name) },
        supportingContent = { Text(text = "${character.status} • ${character.species}") },
        leadingContent = {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = { })
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