package com.example.githubassignment.retrofit

import com.example.githubassignment.models.CreateRepositoryRequestModel
import com.example.githubassignment.models.CreateRepositoryResponseModel
import com.example.githubassignment.models.GetRepositoryResponseModel
import retrofit2.http.*
import rx.Observable

interface RetrofitInterface {

//    @Headers("Content-Type: application/json")
    @GET("users/{user}/repos")
    fun getProfileObserve(@Path("user") user:String): Observable<List<GetRepositoryResponseModel>>

    @Headers("Authorization: Bearer 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")
    @POST("user/repos")
    fun createRepository(@Body createRepositoryRequestModel: CreateRepositoryRequestModel): Observable<CreateRepositoryResponseModel>

}