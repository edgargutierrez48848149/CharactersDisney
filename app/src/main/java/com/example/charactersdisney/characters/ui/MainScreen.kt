package com.example.charactersdisney.characters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.charactersdisney.characters.ui.model.CharacterModel
import com.example.charactersdisney.characters.ui.model.ItemBarModel
import com.example.charactersdisney.characters.ui.navigation.CharacterNavigation
import com.example.charactersdisney.characters.ui.navigation.NavScreens
import com.example.charactersdisney.characters.ui.navigation.characterRute

@Composable
fun MainScreen(characterVm: CharacterVm) {
    val navCortroller = rememberNavController()
    val characters = characterVm.characters.collectAsLazyPagingItems()
    val isConnected by characterVm.isConnected.collectAsState()

    if(isConnected){
        characters.refresh()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {CharSearcBarView(characterVm)},
        bottomBar = { CharNavigationBarView(navCortroller) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            CharacterNavigation(
                navHostController = navCortroller,
                characterList = characters,
                characterVm = characterVm
            )
            if(!isConnected){
                Text(
                    modifier = Modifier.fillMaxWidth().background(Color.Red),
                    text = "No hay Conexion"
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CharSearcBarView(characterVm: CharacterVm) {
    val characterSearchNet  = characterVm.characterSearchNet.observeAsState().value
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var isSearchView by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = text,
                onQueryChange = {
                    isSearchView = false
                    text = it
                    characterVm.onSearchCharacterNet(text)
                },
                onSearch = {
                    isSearchView = true
                },
                expanded = active,
                onExpandedChange = {
                    active = it
                },
                enabled = true,
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    if(isSearchView){
                        Icon(
                            modifier = Modifier.clickable {
                                isSearchView = false
                            },
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "SearchIcon"
                        )
                    }else{
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "SearchIcon"
                        )
                    }
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                isSearchView = false
                                if (text.isNotEmpty()) {
                                    characterVm.onClearCharacterNet()
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "SearchIcon"
                        )
                    }
                },
                interactionSource = null,
            )
        },
        expanded = active,
        onExpandedChange = {
            active = it
        },
        shape = SearchBarDefaults.inputFieldShape,
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = SearchBarDefaults.windowInsets,
        content = {
            LazyColumn {
                characterSearchNet?.size?.let {
                    items(it) { character ->
                        if (isSearchView){
                            CharItemFavoriteView(characterSearchNet[character], characterVm)
                        }else{
                            CharItemSearchView(characterSearchNet[character], characterVm)
                        }
                    }
                }
            }
        },
    )
}

@Composable
fun CharItemSearchView(characterModel: CharacterModel, characterVm: CharacterVm) {
    val isShowDialog = remember { mutableStateOf(false) }

    if (isShowDialog.value){
        CharacterModalScreen(characterModel,isShowDialog,characterVm)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            characterVm.onSearchCharacter(characterModel.id)
            isShowDialog.value = true
        })
    {
        Icon(
            modifier = Modifier.padding(10.dp),
            imageVector = Icons.Default.Clear,
            contentDescription = "Hoistory Icon"
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = characterModel.name)
    }
}

@Composable
fun CharNavigationBarView(navHostController: NavHostController,){
    val itemsBar = listOf(
        ItemBarModel(NavScreens.Home.name,Icons.Filled.Home, Icons.Outlined.Home),
        ItemBarModel(NavScreens.Favorite.name,Icons.Filled.Favorite, Icons.Outlined.Favorite)
    )
    NavigationBar {
        itemsBar.forEachIndexed { index, item ->
            val isSelected = characterRute(navHostController) == item.title
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navHostController.navigate(item.title)
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = isSelected
            )
        }
    }
}


