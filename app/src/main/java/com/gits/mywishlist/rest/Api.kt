package com.gits.mywishlist.rest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("notification/config.php?function=sendNotif")
    fun postNotification(
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<String>
}