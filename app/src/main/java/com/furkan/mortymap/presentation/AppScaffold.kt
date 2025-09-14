package com.furkan.mortymap.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.furkan.mortymap.presentation.characters.CharactersScreen
import com.furkan.mortymap.presentation.locations.LocationsScreen
import com.furkan.mortymap.presentation.navigation.Dest

@Composable
fun AppScaffold(modifier: Modifier = Modifier) {
    val nav = rememberNavController()
    val backStack by nav.currentBackStackEntryAsState()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppBottomBar(
                nav = nav,
                current = backStack?.destination
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = nav,
            startDestination = Dest.Characters.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Dest.Characters.route) { CharactersScreen() }
            composable(Dest.Locations.route) { LocationsScreen() }
        }
    }
}