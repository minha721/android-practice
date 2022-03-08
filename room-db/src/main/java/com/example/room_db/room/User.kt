package com.example.room_db.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, // 고유 ID값
    var name: String = "",
    var age: String = ""
)