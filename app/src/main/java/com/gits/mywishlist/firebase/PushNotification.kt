package com.gits.mywishlist.firebase

import com.gits.mywishlist.model.DataMessage

data class PushNotification(
    val data: DataMessage,
    val to: String
)