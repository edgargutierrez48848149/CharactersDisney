package com.example.charactersdisney.characters.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.charactersdisney.characters.ui.model.CharacterModel

@Composable
fun CharacterScreen(characterList: LazyPagingItems<CharacterModel>, characterVm: CharacterVm) {
    val isConnected by characterVm.isConnected.collectAsState()
    if (!isConnected){
        val characterSearch = characterVm.characterOfflineNet.observeAsState().value
        characterVm.onGetOfflineData()
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)){
            characterSearch?.let {
                items(it) { character ->
                    CharItemView(character, characterVm)
                }
            }
        }
    }else{
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)){
            items(characterList.itemCount) { character ->
                characterList[character]?.let {
                    characterVm.onAddCharacterDb(it)
                    CharItemView(it, characterVm)
                }
            }
        }
    }
}

@Composable
fun CharItemView(characterModel: CharacterModel, characterVm: CharacterVm) {
    val isShowDialog = remember { mutableStateOf(false) }

    if (isShowDialog.value){
        CharacterModalScreen(characterModel,isShowDialog,characterVm)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                isShowDialog.value = true
            },
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = Color(0xFFD6F8F0),
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.Black
        )
    ) {
        Column (Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8)),
                model = characterModel.imageUrl,
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(10.dp),
                text = characterModel.name,
                color = Color.Black,
                style = TextStyle(fontWeight = Bold),
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.padding(10.dp,0.dp,0.dp),
                text = characterModel.createdAt,
                color = Color(0xFF464646),
                fontSize = 12.sp
            )
        }
    }
}