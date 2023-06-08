package com.example.jjobstarbucks.model

data class Home(
    val user: User,
    val appbarImage: String
)

data class User(
    val nickname:String,
    var starCount : Int,
    var totalCount : Int,
)