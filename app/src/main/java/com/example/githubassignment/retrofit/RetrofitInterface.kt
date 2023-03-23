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