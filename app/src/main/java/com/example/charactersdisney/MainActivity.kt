package com.example.charactersdisney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.charactersdisney.characters.ui.CharacterVm
import com.example.charactersdisney.characters.ui.MainScreen
import com.example.charactersdisney.ui.theme.CharactersDisneyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val characterVm:CharacterVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterVm.startConnectivity(this)
        enableEdgeToEdge()
        setContent {
            CharactersDisneyTheme {
                MainScreen(characterVm)
            }
        }
    }
}