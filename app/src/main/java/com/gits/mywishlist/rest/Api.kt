package com.gits.mywishlist.rest

import com.gits.mywishlist.firebase.PushNotification
import com.gits.mywishlist.utils.Constants.Companion.CONTENT_TYPE
import com.gits.mywishlist.utils.Constants.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}