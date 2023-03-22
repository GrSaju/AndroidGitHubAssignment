package com.example.githubassignment.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
entities =[TblRepositoryList::class], version = 1
)

abstract class OfflineRoomDatabase: RoomDatabase() {

    companion object {
        private var myAppDatabase: OfflineRoomDatabase? = null
        fun getDataBaseInstance(context: Context): OfflineRoomDatabase? {
            if (myAppDatabase == null) {
                myAppDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    OfflineRoomDatabase::class.java, "TblAssignment"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return myAppDatabase
        }
    }

    abstract fun iTblGetRepository(): ITblGetRepositories?

}