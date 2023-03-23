package com.example.githubassignment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubassignment.models.CreateRepositoryResponseModel
import com.example.githubassignment.models.GetRepositoryResponseModel
import com.example.githubassignment.repository.MainRepository

class MainViewModel: ViewModel() {

    private var getUserRepositoryData = MutableLiveData<List<GetRepositoryResponseModel>>()
    private var createRepositoryData = MutableLiveData<CreateRepositoryResponseModel>()

    fun getUserRepositoryData(user: String) : MutableLiveData<List<GetRepositoryResponseModel>>{
        getUserRepositoryData = MainRepository.getRepository(user)
        return getUserRepositoryData
    }


    fun createRepositoryData(name: String) : MutableLiveData<CreateRepositoryResponseModel> {
        createRepositoryData = MainRepository.createRepository(name)
        return createRepositoryData

    }

}