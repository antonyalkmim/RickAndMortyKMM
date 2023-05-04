package com.example.rickandmortykmm.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedCharacters(
    @SerialName("results")
    val results: List<Character>
)