package com.example.githubassignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubassignment.models.CreateRepositoryRequestModel
import com.example.githubassignment.models.CreateRepositoryResponseModel
import com.example.githubassignment.models.GetRepositoryResponseModel
import com.example.githubassignment.retrofit.ApiClient
import retrofit2.adapter.rxjava.Result.response
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


object MainRepository {
    var subscriptions: Subscription? = null

    /**
     * This function is for handling of the thread
     * using RX Java and storing as LiveData for
     * getting the list of  Repository
     * */
    fun getRepository(user: String) : MutableLiveData<List<GetRepositoryResponseModel>> {
        val liveRepositoryUsers: MutableLiveData<List<GetRepositoryResponseModel>> = MutableLiveData()
        subscriptions = ApiClient.apiService.getProfileObserve(user)
            .observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : Observer<List<GetRepositoryResponseModel>>   {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Log.e("getRepositoryError", e.toString())

                }

                override fun onNext(t: List<GetRepositoryResponseModel>) {
                    if(t!=null && t.isNotEmpty()){
                        Log.e("getRepositoryLiveData", t.toString())
                        liveRepositoryUsers.value = t
                    }

                }

            })
        return liveRepositoryUsers
    }



    /**
     * This function is for handling of the thread
     * using RX Java and storing as LiveData for creating Repository
     * */
    fun createRepository(name: String): MutableLiveData<CreateRepositoryResponseModel>{
        val liveCreateRepository: MutableLiveData<CreateRepositoryResponseModel> = MutableLiveData()

        val createRepositoryRequestModel  = CreateRepositoryRequestModel()
        createRepositoryRequestModel.name = name
//        createRepositoryRequestModel.description = description

        subscriptions = ApiClient.apiService.createRepository(createRepositoryRequestModel)
            .observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : Observer<CreateRepositoryResponseModel> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Log.e("createRepositoryError", e.toString())

                }

                override fun onNext(t: CreateRepositoryResponseModel) {
                    if(t!=null){
                        Log.e("createRepositoryLiveData", t.toString())
                        liveCreateRepository.value = t
                    }

                }

            })
        return liveCreateRepository
    }

}