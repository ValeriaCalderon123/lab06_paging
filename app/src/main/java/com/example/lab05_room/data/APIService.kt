package com.example.lab05_room.data

import com.example.lab05_room.data.entity.AnimalsResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("api/v3/species/page/0?token=9bb4facb6d23f48efbf424bb05c0c1ef1cf6f468393bc745d42179ac4aca5fee")
    suspend fun getAnimals(): Response<AnimalsResponse>
}