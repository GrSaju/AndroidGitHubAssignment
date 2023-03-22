package com.example.githubassignment.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TblRepositoryName")
class TblRepositoryList {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    lateinit var repositoryName: String
    lateinit var fullName: String
    lateinit var ownerName: String
    lateinit var htmlUrl: String
    lateinit var description: String
}