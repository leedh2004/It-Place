package com.example.it_place.retrofit.dto

//data 클래스
object Dtos{

data class TestDto (
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var completed: Boolean = false)

}