package com.example.jjobstarbucks.model

data class Home(
    val user: User,
    val appbarImage: String,
    val banner: Banner
)

data class User(
    val nickname:String,
    var starCount : Int,
    var totalCount : Int,
)

data class Banner(
    var image: String,
    var contentDescription : String
)