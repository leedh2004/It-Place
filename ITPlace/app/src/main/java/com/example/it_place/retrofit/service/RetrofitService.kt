package com.example.it_place.retrofit.service

import com.example.it_place.retrofit.dto.Dtos
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    /**
     * api GET Test
     */
    @GET("todos/{user_id}")
    fun getTests(@Path("user_id") userId:Int): Call<Dtos.TestGetDto>

    @POST("posts")
    fun postTests(@Body req : Dtos.TestPostReqDto) : Call<Dtos.TestPostResDto>


}