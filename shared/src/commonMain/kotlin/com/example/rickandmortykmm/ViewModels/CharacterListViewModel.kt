package com.example.rickandmortykmm.ViewModels

import RickAndMortyService
import com.example.rickandmortykmm.Models.Character

class CharacterListViewModel {

    private val service = RickAndMortyService()

    suspend fun loadCharacters(): State {
        return State.Items(
            service.listCharacters()
        )
    }

    sealed class State {
        object Error: State()
        object Loading: State()
        object Empty: State()
        data class Items(val items: List<Character>): State()
    }
}