package com.pine.mytrademetest.net

import com.pine.mytrademetest.net.beans.ListingItemBaseBean
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {

    @GET("/Xauth/AccessToken")
    fun accessToken( ): Call<String>;

    @GET("/v1/listings/latest.json")
    fun latest( ): Call<ListingItemBaseBean>;

}