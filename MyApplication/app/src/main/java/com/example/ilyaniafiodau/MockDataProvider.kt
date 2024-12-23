package com.example.ilyaniafiodau

import kotlinx.coroutines.delay
import java.io.File

data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)

object MockApi {
    private val Places = listOf(
        Place(1, "Classroom 395", "VK Education residence", "https://www.google.com/url?sa=i&url=https%3A%2F%2Feducation.vk.company%2Fcentrum%2Fkursy-baumanka&psig=AOvVaw3Ya83uLPgwfGEa0D4o-HTx&ust=1734995967990000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCMCMuOTBvIoDFQAAAAAdAAAAABAE"),
        Place(2, "Bauman Racing Team", "Where engineering meets motorsport", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.symmetron.ru%2Fnews%2Fgonochnyy-bolid-bauman-racing-team-s-nashimi-komponentami%2F&psig=AOvVaw1K7AXCDNlI6bT6K1rBfUe1&ust=1734996045819000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCMCPxYnCvIoDFQAAAAAdAAAAABAQ")
    )

    suspend fun getPlaces(delayMillis: Long = 2000): List<Place> {
        delay(delayMillis)
        return Places
    }

    suspend fun getPlaceInfo(PlaceId: Int, delayMillis: Long = 2000): Place? {
        delay(delayMillis)
        return Places.find { it.id == PlaceId }
    }
}




// Assuming you have a Place data class:
//data class Place(val id: Int, val name: String, val imageUrl: String)

fun getPlaceById(placeId: Int): Place? {
    // Load places from a file (simulated, replace with actual file reading in real app)
    val fileContent = File("places.txt").readLines()

    // Parse the places from the file content
    val places = fileContent.mapNotNull { line ->
        val parts = line.split(",")
        if (parts.size == 4) {
            try {
                val id = parts[0].toInt()
                val name = parts[1]
                val description = parts[2]
                val imageUrl = parts[3]
                Place(id, name, description, imageUrl)
            } catch (e: NumberFormatException) {
                null
            }
        } else {
            null
        }
    }

    // Find and return the place with the specified placeId
    return places.find { it.id == placeId }
}