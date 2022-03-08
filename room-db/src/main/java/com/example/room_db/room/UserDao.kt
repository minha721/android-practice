package com.example.room_db.room

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun setInsertUser(user: User)

    @Update
    fun setUpdateUser(user: User)

    @Delete
    fun setDeleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getUserAll(): List<User>

}