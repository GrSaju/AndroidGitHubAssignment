package com.example.githubassignment.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class CreateRepositoryRequestModel {

    @SerializedName("name")
    @Expose
    var name: String? = null
//
//    @SerializedName("description")
//    @Expose
//    var description: String? = null

}