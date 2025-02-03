package com.example.charactersdisney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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