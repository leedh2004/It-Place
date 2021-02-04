package com.example.it_place.retrofit.service

import com.example.it_place.retrofit.dto.Dtos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    /**
     * api Test
     */
    @GET("todos/{user_id}")
    fun getTests(@Path("user_id") userId:Int): Call<Dtos.TestDto>

}