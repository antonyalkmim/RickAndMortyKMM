package com.example.rickandmortykmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmortykmm.ViewModels.CharacterListViewModel
import com.example.rickandmortykmm.Models.*
import com.example.rickandmortykmm.ViewModels.CharacterListViewModel.State

class MainActivity : ComponentActivity() {

    private val viewModel = CharacterListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var state: State by remember {
                        mutableStateOf(State.Empty)
                    }

                    LaunchedEffect(state) {
                        state = viewModel.loadCharacters()
                    }

                    when(state) {
                        is State.Loading -> CircularProgressIndicator()
                        is State.Empty -> EmptyView()
                        is State.Items -> CharacterList((state as State.Items).items)
                        else -> EmptyView()
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterList(items: List<Character>) {
    LazyColumn {
        items(items) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Id: ${it.id}",
                    fontWeight = FontWeight(weight = 700)
                )
                Text(text = it.name)
                Divider()
            }
        }
    }
}

@Composable
fun EmptyView() {
    Text(text = "Nenhum personagem de Rick n Morty foi encontrado")
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CharacterList(emptyList())
    }
}
