package com.gits.mywishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gits.mywishlist.firebase.FirebaseService
import com.gits.mywishlist.firebase.PushNotification
import com.gits.mywishlist.model.DataMessage
import com.gits.mywishlist.rest.RetrofitClient
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TOPIC = "/topics/myTopic2"

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
            etToken.setText(it.token)
        }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        btnSend.setOnClickListener {
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            val recipientToken = etToken.text.toString()
            if(title.isNotEmpty() && message.isNotEmpty() && recipientToken.isNotEmpty()) {
                PushNotification(
                        DataMessage(title, message),
                        recipientToken
                ).also {
                    sendNotification(it)
                }
            }
        }

        val refreshToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refresh token $refreshToken")
    }



    private fun sendNotification(it: PushNotification) {
        val response = RetrofitClient.getApi().postNotification(it.data.title,it.data.message)
        response.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    Toast.makeText(this@MainActivity,"Success Notification",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }

        })

    }
}
