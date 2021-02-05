package com.example.it_place.retrofit.dto

//data 클래스
object Dtos{
    
    //Get 테스트
    data class TestGetDto (
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var completed: Boolean = false)

    
    //Post 테스트
    data class TestPostReqDto(
    var userId : Int = 0,
    var title : String = "",
    var body : String = "")

    
    //Post 요청 후 응답
    data class TestPostResDto(
        var id: Int = 0,
        var title: String = "",
        var body: String = "",
        var userId: Int = 0)
}