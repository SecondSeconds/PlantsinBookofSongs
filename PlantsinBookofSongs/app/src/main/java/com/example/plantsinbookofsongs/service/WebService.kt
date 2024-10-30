package com.example.plantsinbookofsongs.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebService {
    companion object{
        //要访问的 Restful Service 基地址： 百度ai
        var baseUrl="https://aip.baidubce.com/"
        val service:WebService by lazy {
            val retrofit= Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            retrofit.create(WebService::class.java)
        }
    }
    //当token失效时，获得新token
    @POST("oauth/2.0/token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("grant_type") grantType:String?,
        @Field("client_id") clientId:String?,
        @Field("client_secret") clientSecret:String?
    ): Response<ResponseBody?>?

    //POST：百度植物识别
    //使用协程，挂起函数

    //url
    @POST("rest/2.0/image-classify/v1/plant")
    @FormUrlEncoded
    suspend fun getRecognitionResultByUrl(
        @Field("access_token") accessToken: String?,
        @Field("url") url: String?
    ): Response<ResponseBody?>?

    //url+descriptipn
    @POST("rest/2.0/image-classify/v1/plant")
    @FormUrlEncoded
    suspend fun getFullRecognitionResultByUrl(
        @Field("access_token") accessToken: String?,
        @Field("url") url: String?,
        @Field("baike_num")baike_num:Int?
    ): Response<ResponseBody?>?

    //image
    @POST("rest/2.0/image-classify/v1/plant")
    @FormUrlEncoded
    suspend fun getRecognitionResultByImage(
        @Field("access_token") accessToken: String?,
        @Field("image") image: String?
    ): Response<ResponseBody?>?

    //image+descriptipn
    @POST("rest/2.0/image-classify/v1/plant")
    @FormUrlEncoded
    suspend fun getFullRecognitionResultByImage(
        @Field("access_token") accessToken: String?,
        @Field("image") image: String?,
        @Field("baike_num")baike_num:Int?
    ): Response<ResponseBody?>?
}