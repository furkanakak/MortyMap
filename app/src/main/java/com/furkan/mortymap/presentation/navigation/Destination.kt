package com.furkan.mortymap.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Dest(val route: String, val label: String, val icon: ImageVector) {
    data object Characters : Dest("characters", "Characters", Icons.Outlined.Person)
    data object Locations  : Dest("locations",  "Locations",  Icons.Outlined.LocationOn)
}

val bottomDestinations = listOf(Dest.Characters, Dest.Locations)