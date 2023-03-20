package com.example.happyplaces.models

data class HappyPlaceModel(

    var id: Int,
    var title: String,
    var image: String,
    var date: String,
    var location: String,
    var latitude: Double,
    var longitude: Double,
    var description: String
)