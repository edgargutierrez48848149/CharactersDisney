package com.example.charactersdisney.characters.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.charactersdisney.characters.domain.AddCharacterDbUc
import com.example.charactersdisney.characters.domain.AddCharacterOfflineDbUc
import com.example.charactersdisney.characters.domain.DeleteCharacterDbUc
import com.example.charactersdisney.characters.domain.GetCharacterSearchDbUc
import com.example.charactersdisney.characters.domain.GetCharacterSearchNetUc
import com.example.charactersdisney.characters.domain.GetCharacterrDbUc
import com.example.charactersdisney.characters.domain.GetCharacterrNetUc
import com.example.charactersdisney.characters.ui.model.CharacterModel
import com.example.charactersdisney.common.CharacterConnectivityObserver
import com.example.charactersdisney.common.UiState
import com.example.charactersdisney.common.UiState.Succes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterVm @Inject constructor(
    getCharacterrNetUc: GetCharacterrNetUc,
    getCharacterUc: GetCharacterrDbUc,
    private val getCharacterSearchNetUc: GetCharacterSearchNetUc,
    private val addCharacterDbUc: AddCharacterDbUc,
    private val getCharacterSearchDbUc: GetCharacterSearchDbUc,
    private val deleteCharacterDbUc: DeleteCharacterDbUc,
    private val addCharacterOfflineDbUc: AddCharacterOfflineDbUc
):ViewModel() {

    lateinit var isConnected:StateFlow<Boolean>

    val characters: Flow<PagingData<CharacterModel>> = getCharacterrNetUc.invoke()

    val uiState: StateFlow<UiState> = getCharacterUc().map(::Succes)
        .catch { UiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    private val _characterSearch = MutableLiveData<CharacterModel>(null)
    val characterSearch = _characterSearch

    private val _characterSearchNet = MutableLiveData<List<CharacterModel>>(listOf())
    val characterSearchNet = _characterSearchNet

    private val _characterOfflineNet = MutableLiveData<List<CharacterModel>>(listOf())
    val characterOfflineNet = _characterOfflineNet

    fun startConnectivity(context: Context){
        isConnected = CharacterConnectivityObserver(context)
            .isConnected
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),false
            )

    }

    fun onAddCharacter(characterModel: CharacterModel){
        viewModelScope.launch {
            addCharacterDbUc.invoke(characterModel)
        }
    }

    fun onRemoveCharacter(characterModel: CharacterModel){
        viewModelScope.launch {
            deleteCharacterDbUc.invoke(characterModel)
        }
    }

    fun onSearchCharacter(id:Long){
        viewModelScope.launch {
            _characterSearch.value = getCharacterSearchDbUc.invoke(id)
        }
    }

    fun onSearchCharacterNet(name:String){
        viewModelScope.launch {
            _characterSearchNet.value = getCharacterSearchNetUc.invoke(name)
        }
    }

    fun onAddCharacterDb(characterModel: CharacterModel){
        viewModelScope.launch {
            addCharacterOfflineDbUc.invoke(characterModel)
        }
    }

    fun onGetOfflineData(){
        viewModelScope.launch {
            _characterOfflineNet.value = addCharacterOfflineDbUc.invoke()
        }
    }

    fun onClearCharacterNet(){
        _characterSearchNet.value = listOf()
    }
}