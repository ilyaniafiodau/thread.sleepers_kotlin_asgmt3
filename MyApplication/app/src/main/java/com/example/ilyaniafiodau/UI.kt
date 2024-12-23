package com.example.ilyaniafiodau

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

/*@Composable
fun PlaceListScreen(viewModel: PlacesViewModel, onPlaceClick: (Int) -> Unit) {
    val state: UiState<List<Place>> by viewModel.PlacesState.collectAsState()

    when (state) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No sights available",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
        is UiState.Success -> {
            val places = (state as UiState.Success<List<Place>>).data
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(places) { place ->
                    PlaceInfoScreen(place, onPlaceClick)
                }
            }
        }
        is UiState.Error -> {
            val errorMessage = (state as UiState.Error).message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Loading Failure: $errorMessage",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun PlaceInfoScreen(place: Place, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(place.id) }
    ) {
        Column {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = place.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}*/

/*@Composable
fun PlaceListScreen(viewModel: PlacesViewModel, onPlaceClick: (Int) -> Unit) {
    val state: UiState<List<Place>> by viewModel.PlacesState.collectAsState()

    when (state) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No sights available",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
        is UiState.Success -> {
            val places = (state as UiState.Success<List<Place>>).data
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(places) { place ->
                    // Pass place.id to the PlaceInfoScreen
                    PlaceInfoScreen(place.id, onClick = onPlaceClick)
                }
            }
        }
        is UiState.Error -> {
            val errorMessage = (state as UiState.Error).message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Loading Failure: $errorMessage",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}*/

@Composable
fun PlaceListScreen(viewModel: PlacesViewModel, onPlaceClick: (Int) -> Unit) {
    val state: UiState<List<Place>> by viewModel.PlacesState.collectAsState()

    when (state) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No sights available",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
        is UiState.Success -> {
            val places = (state as UiState.Success<List<Place>>).data
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(places) { place ->
                    // Pass place.id to PlaceCard, where the click logic is handled
                    PlaceCard(place, onPlaceClick)
                }
            }
        }
        is UiState.Error -> {
            val errorMessage = (state as UiState.Error).message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Loading Failure: $errorMessage",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun PlaceCard(place: Place, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(place.id) }  // Use onClick here
    ) {
        Column {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = place.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Composable
fun PlaceInfoScreen(placeId: Int) {
    // Use your ViewModel to fetch the place details using the placeId
    val place = getPlaceById(placeId) // Assume this is a method to fetch place from a repository or ViewModel

    if (place != null) {
        // Display the place details
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = place.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    } else {
        Text("Place not found")
    }
}

