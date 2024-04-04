package com.creativeitinstitute.roomdatabase.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Update
    fun editUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM USER")
    fun getAllUser():List<User>
}