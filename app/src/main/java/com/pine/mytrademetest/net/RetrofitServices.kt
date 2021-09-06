package com.pine.mytrademetest.net

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitServices {


    @GET("/v1/listings/latest.json")
    fun latest( ): Call<String>;

}