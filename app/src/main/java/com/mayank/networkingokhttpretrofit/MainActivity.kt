package com.mayank.networkingokhttpretrofit

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mayank.networkingokhttpretrofit.Client.retrofitCallBack
import com.mayank.networkingokhttpretrofit.Client.service
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
//                val gson = Gson().fromJson(it.body?.string(),Github::class.java)                                     //  3rd Method
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
//                    val gson = Gson().fromJson(response.body?.string(),Github::class.java)                   // 1st Method
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

        val inflater = LayoutInflater.from(this)

        AlertDialog.Builder(this).setTitle("Hello").setMessage("Hello Everyone")
            .setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
                service.listUsers().enqueue(retrofitCallBack { response, throwable ->
                    response?.let {
                        runOnUiThread {
                            textView.text = it.body()?.items.toString()
                        }
                    }
                })
            }
            .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }.setCancelable(true)
            .show()
    }


//    fun okhttpCallback(fn: (Response?,Throwable?)->Unit): Callback{
//        return object :Callback{
//            override fun onFailure(call: Call, e: IOException) = fn(null,e)
//
//            override fun onResponse(call: Call, response: Response) = fn(response,null)                       //2nd method
//
//        }
//    }

    //Retrofit

}
