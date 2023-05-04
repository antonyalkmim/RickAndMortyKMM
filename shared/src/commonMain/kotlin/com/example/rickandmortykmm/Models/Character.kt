package com.example.rickandmortykmm.Models

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: String,
    val name: String,
    val species: String,
    val url: String
)