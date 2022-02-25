package com.example.androidinterntask

data class Item(
    val username: String,
    val email: String,
    var title: String = "None",
    val color: Int,
    val url: String
)