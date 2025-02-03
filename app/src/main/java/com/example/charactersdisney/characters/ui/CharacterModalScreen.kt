package com.example.charactersdisney.characters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.charactersdisney.characters.ui.model.CharacterModel
import com.example.charactersdisney.common.gradientBackground

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CharacterModalScreen(
    characterModel: CharacterModel,
    isShowDialog: MutableState<Boolean>,
    characterVm: CharacterVm
) {
    val characterSearch = characterVm.characterSearch.observeAsState().value
    val sheetState = rememberModalBottomSheetState()

    characterVm.onSearchCharacter(characterModel.id)

    ModalBottomSheet(
        containerColor = Color(0xff9298e2),
        sheetState = sheetState,
        onDismissRequest = {
            isShowDialog.value = false
        }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            val (background, image, toggle, column) = createRefs()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .gradientBackground(
                        listOf(Color(0xFFF8D6DE),Color(0xFFB994E1),Color(0xff9298e2)), angle = 93f
                    )
                    .constrainAs(background) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )

            IconToggleButton(
                modifier = Modifier
                    .constrainAs(toggle) {
                        start.linkTo(image.end)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                checked = characterSearch != null,
                onCheckedChange = {
                    if (characterSearch != null) {
                        characterVm.onRemoveCharacter(characterModel)
                    } else {
                        characterVm.onAddCharacter(characterModel)
                    }
                    isShowDialog.value = false
                },
            ) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = if (characterSearch != null) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .constrainAs(column) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(background.bottom)
                }) {
                Text(
                    modifier = Modifier.padding(10.dp, 100.dp, 0.dp),
                    text = characterModel.name,
                    color = Color.Black,
                    style = TextStyle(fontWeight = Bold),
                    fontSize = 28.sp
                )

                Text(
                    modifier = Modifier.padding(10.dp,0.dp,0.dp),
                    text = characterModel.createdAt,
                    color = Color(0xFF464646),
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(10.dp,0.dp,0.dp),
                    text = characterModel.updatedAt,
                    color = Color(0xFF464646),
                    fontSize = 16.sp
                )

                DetailsCharacter("Peliculas",characterModel.shortFilms, Color(0xFFD6D8F8))

                DetailsCharacter("Shows de Television",characterModel.tvShows, Color(0xFFF8D6DE))

                DetailsCharacter("Videojuegos",characterModel.videoGames, Color(0xFFD6F8F0))

                DetailsCharacter("Parque de Diversiones",characterModel.parkAttractions, Color(0xFFF8F5D6))

                DetailsCharacter("Aliados",characterModel.allies, Color(0xFFD9F8D6))

                DetailsCharacter("Enemigos",characterModel.enemies, Color(0xFFF8D6D9))
            }

            AsyncImage(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(50))
                    .border(8.dp, Color.White, shape = RoundedCornerShape(50))
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(background.bottom)
                        end.linkTo(parent.end)
                        bottom.linkTo(background.bottom)
                    },
                model = characterModel.imageUrl,
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun DetailsCharacter(title:String,listData:List<String>,color: Color){
    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = color,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            text = title,
            fontSize = 18.sp,
            style = TextStyle(fontWeight = Bold)
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp,0.dp,10.dp,10.dp)
        ){
            listData.forEach {item->
                Text(
                    text = item,
                    fontSize = 12.sp,
                    style = TextStyle(fontStyle = Italic))
            }
        }
    }
}