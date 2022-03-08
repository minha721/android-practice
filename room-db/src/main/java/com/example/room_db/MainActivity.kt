package com.example.room_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.room_db.databinding.ActivityMainBinding
import com.example.room_db.room.User
import com.example.room_db.room.UserDao
import com.example.room_db.room.UserDatabase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var database: UserDatabase
    lateinit var mUserDao: UserDao
    lateinit var userList: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "minha_db")
            .fallbackToDestructiveMigration()   // 스키마(Database) 버전 변경 가능
            .allowMainThreadQueries()           // Main Thread에서 DB에 입출력을 가능하게 함
            .build()

        mUserDao = database.userDao()           // 인터페이스 객체 할당

        binding.btnInsert.setOnClickListener {
            val insertUser = User()
            insertUser.name = binding.etName.text.toString()
            insertUser.age = binding.etAge.text.toString()
            mUserDao.setInsertUser(insertUser)

            binding.etName.text.clear()
            binding.etAge.text.clear()
        }

        binding.btnSelect.setOnClickListener {
            userList = mUserDao.getUserAll()

            binding.tvResult.text = ""

            for(user in userList) {
                binding.tvResult.append("name : ${user.name}, age : ${user.age}\n")
            }
        }

        binding.btnDelete.setOnClickListener {
            userList = mUserDao.getUserAll()

            for(user in userList) {
                mUserDao.setDeleteUser(user)
            }

            binding.tvResult.text = ""
        }
    }
}