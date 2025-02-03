package com.example.charactersdisney.characters.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import com.example.charactersdisney.characters.ui.model.CharacterModel
import com.example.charactersdisney.common.UiState

@Composable
fun CharacterFavoriteScreen(
    characterVm: CharacterVm
) {
    val lifeCycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<UiState>(
        initialValue = UiState.Loading,
        key1 = lifeCycle,
        key2 = characterVm
    ) {
        lifeCycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            characterVm.uiState.collect { value = it }
        }
    }

    when (uiState) {
        UiState.Loading -> {}
        is UiState.Succes -> {
            val characterList = (uiState as UiState.Succes).listCharacter
            LazyColumn {
                items(characterList.size) { character ->
                    CharItemFavoriteView(characterList[character], characterVm)
                }
            }
        }

        is UiState.Error -> {}
    }
}

@Composable
fun CharItemFavoriteView(characterModel: CharacterModel, characterVm: CharacterVm) {
    val isShowDialog = remember { mutableStateOf(false) }

    if (isShowDialog.value){
        CharacterModalScreen(characterModel,isShowDialog,characterVm)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,5.dp)
            .clickable {
            isShowDialog.value = true
        },
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = Color(0xFFD9EAFF),
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )

    ) {
        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .padding(5.dp)
                    .size(60.dp)
                    .clip(RoundedCornerShape(50))
                    .border(5.dp, Color.White, shape = RoundedCornerShape(50)),
                model = characterModel.imageUrl,
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = characterModel.name,
                fontSize = 18.sp,
                style = TextStyle(fontWeight = Bold)
            )
        }
    }
}