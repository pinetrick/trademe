package com.pine.mytrademetest.net

import com.pine.mytrademetest.net.beans.ListingItemBaseBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("/Xauth/AccessToken")
    fun accessToken(): Call<String>;

    @GET("/v1/listings/latest.json")
    fun latest( @Query("rows") rows: Int = 20): Call<ListingItemBaseBean>;

}