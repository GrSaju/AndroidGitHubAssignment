package com.example.githubassignment.roomDatabase

import androidx.room.*

@Dao
interface ITblGetRepositories {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(insertRepositories: TblRepositoryList)

    @Query("SELECT * FROM TblRepositoryName")
    fun getOfflineRepositoryList(): List<TblRepositoryList>

    @Delete
    fun reset(tblRepositoryList: List<TblRepositoryList>)
}