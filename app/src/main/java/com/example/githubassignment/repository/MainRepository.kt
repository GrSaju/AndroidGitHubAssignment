package com.example.githubassignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubassignment.models.GetRepositoryResponseModel
import com.example.githubassignment.retrofit.ApiClient
import rx.Observer
import rx.Subscription
import rx.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers


object MainRepository {
    var subscriptions: Subscription? = null

    /**
     * This function is for handling of the thread
     * using RX Java and storing as LiveData
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

}