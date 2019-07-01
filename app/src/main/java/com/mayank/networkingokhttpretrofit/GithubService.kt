package com.mayank.networkingokhttpretrofit

import retrofit2.Call
import retrofit2.http.GET

interface GithubService{
    @GET("search/users?q=Mayank%20Gupta")
    fun listUsers(): Call<GithubResponse>

    @GET("search/users?q=%22Pulkit%20Aggarwal%22")
    fun listRepos(): Call<GithubResponse>
}