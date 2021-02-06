package com.example.it_place.retrofit.service

import com.example.it_place.Model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    /**
     * 1.이미지 파일 업로드
     */
    @Multipart
    @POST("upload")
    fun uploadImage(@Part imgFile: MultipartBody.Part): Call<Image>

    /**
     * 2.유저 생성
     */
    @POST("user")
    fun createUser(@Body user: User): Call<User>


    /**
     * 어플 처음에 들어갔을때 회원가입
     */
    @POST("user")
    fun enterApp(@Body enterApp: EnterApp): Call<EnterApp>


    /**
     * 3.방 생성
     */
    @POST("room")
    fun createPlace(
        @Header("Authorization") uid: String,
        @Body placeForm: PlaceForm): Call<PlaceFormRes>

    /**
     * 3.방 생성
     */
    @POST("room")
    fun createPlaceNoImg(
        @Header("Authorization") uid: String,
        @Body placeFormNoImg: PlaceFormNoImg): Call<PlaceFormRes>

    /**
     * 4.모든 방 목록 조회
     */
    @GET("room/all")
    fun placeList(): Call<List<Place>>

    /**
     * 5.방 검색
     */
    @GET("room/search/{query}")
    fun searchPlace(@Path("query") query: String): Call<List<Place>>

    /**
     * 6.내가 있는 방 조회
     */
    @GET("room")
    fun myPlace(@Header("Authorization") uid: String): Call<List<Place>>

}