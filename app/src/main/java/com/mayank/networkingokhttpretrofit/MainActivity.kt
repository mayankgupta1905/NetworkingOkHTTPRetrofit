package com.mayank.networkingokhttpretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("https://api.github.com/search/users?q=%22Mayank%20Gupta%22")
//            .build()

//        okHttpClient.newCall(getUrl("search/users?q=%22Mayank%20Gupta%22"))
//            .enqueue(okhttpCallback{response, throwable ->
//            response?.let{
//                val gson = Gson().fromJson(it.body?.string(),Github::class.java)   //  3rd Method
//                runOnUiThread{
//                    textView.text = gson.items.toString()
//                }
//            }
//        })


//        client.newCall(request).enqueue(object :Callback{
//            override fun onFailure(call: Call, e: IOException) {
//               }
//
//            override fun onResponse(call: Call, response: Response) {
//                if(response.isSuccessful){
//                    val gson = Gson().fromJson(response.body?.string(),Github::class.java)    // 1st Method
//                    runOnUiThread{
//                        textView.text = gson.items.toString()
//                    }
//                }
//            }
//       })
//        client.newCall(request).execute() // needs Async Task To be Implemented
        Client.service.listUsers().enqueue(object :Callback<GithubResponse> {
            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {

                runOnUiThread {
                    textView.text = response.body()?.items.toString()
                }

            }

        })
    }


//    fun okhttpCallback(fn: (Response?,Throwable?)->Unit): Callback{
//        return object :Callback{
//            override fun onFailure(call: Call, e: IOException) = fn(null,e)
//
//            override fun onResponse(call: Call, response: Response) = fn(response,null)   //2nd method
//
//        }
//    }

    //Retrofit

}
