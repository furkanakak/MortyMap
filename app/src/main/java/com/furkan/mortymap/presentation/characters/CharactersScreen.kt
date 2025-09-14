package com.furkan.mortymap.presentation.characters

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.furkan.mortymap.data.model.Character
import com.furkan.mortymap.presentation.base.PagingList

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val characters: LazyPagingItems<Character> = viewModel.characters.collectAsLazyPagingItems()

    PagingList(items = characters, modifier = modifier) { character ->
        CharacterItem(character)
    }
}