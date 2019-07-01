package com.mayank.networkingokhttpretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.github.com/search/users?q=%22Mayank%20Gupta%22")
            .build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    val gson = Gson().fromJson(response.body?.string(),Github::class.java)
                    runOnUiThread{
                        textView.text = gson.items.toString()
                    }
                }
            }
       })
//        client.newCall(request).execute() // needs Async Task To be Implemented
    }
}
