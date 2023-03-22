package com.example.githubassignment.retrofit

import com.example.githubassignment.models.GetRepositoryResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface RetrofitInterface {

    @GET("users/{user}/repos")
    fun getProfileObserve(@Path("user") user:String): Observable<List<GetRepositoryResponseModel>>

}