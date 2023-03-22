package com.example.githubassignment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubassignment.models.GetRepositoryResponseModel
import com.example.githubassignment.repository.MainRepository

class MainViewModel: ViewModel() {

    private var getUserRepositoryData = MutableLiveData<List<GetRepositoryResponseModel>>()

    fun getUserRepositoryData(user: String) : MutableLiveData<List<GetRepositoryResponseModel>>{
        getUserRepositoryData = MainRepository.getRepository(user)
        return getUserRepositoryData

    }


}