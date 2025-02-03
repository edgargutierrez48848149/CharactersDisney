package com.example.charactersdisney.characters.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.compose.LazyPagingItems
import com.example.charactersdisney.characters.ui.CharacterFavoriteScreen
import com.example.charactersdisney.characters.ui.CharacterScreen
import com.example.charactersdisney.characters.ui.CharacterVm
import com.example.charactersdisney.characters.ui.model.CharacterModel

@Composable
fun CharacterNavigation(
    navHostController: NavHostController,
    characterList: LazyPagingItems<CharacterModel>,
    characterVm: CharacterVm
) {
    NavHost(
        navController = navHostController,
        startDestination = NavScreens.Home.name
    ){
        composable(NavScreens.Home.name) {
            CharacterScreen(characterList,characterVm)
        }

        composable(NavScreens.Favorite.name) {
            CharacterFavoriteScreen(characterVm)
        }
    }
}

@Composable
fun characterRute(navHostController: NavHostController):String? =
    navHostController.currentBackStackEntryAsState().value?.destination?.route
