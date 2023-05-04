package com.example.rickandmortykmm.ViewModels

import RickAndMortyService

class CharacterListViewModel {

    private val service = RickAndMortyService()

    suspend fun loadCharactersNames(): String {
        return service.listCharacters()
            .map { it.name }
            .joinToString(separator = "\n")
    }

}