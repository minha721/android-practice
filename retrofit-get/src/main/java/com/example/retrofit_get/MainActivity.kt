package com.example.retrofit_get

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.retrofit_get.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btnOk.setOnClickListener {
            userId = binding.etId.text.toString()

            if(userId != "") {
                callGithubAPI(userId)
                subscribeViewModel()
            } else {
                Toast.makeText(this, "Github ID를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun callGithubAPI(userId: String) = mainViewModel.requestUserInfo(userId)

    private fun subscribeViewModel() {
        mainViewModel.okCode.observe(this){
            if(it) {
                val userData = mainViewModel.userData

                if(userData.userId != "") {
                    showInfoViews()
                    Glide.with(this).load(userData.image).override(150, 150).into(binding.ivImage)
                    binding.tvName.text = userData.name
                    binding.tvId.text = userData.userId
                    binding.tvRepos.text = userData.repos.toString()
                } else {
                    notShowInfoViews()
                    Toast.makeText(this, "입력하신 ID는 존재하지 않습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }

            } else {
                notShowInfoViews()
                Toast.makeText(this, "입력하신 ID의 상세 정보 조회를 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showInfoViews() {
        binding.ivImage.visibility = VISIBLE
        binding.name.visibility = VISIBLE
        binding.tvName.visibility = VISIBLE
        binding.id.visibility = VISIBLE
        binding.tvId.visibility = VISIBLE
        binding.repos.visibility = VISIBLE
        binding.tvRepos.visibility = VISIBLE
    }

    private fun notShowInfoViews() {
        binding.ivImage.visibility = GONE
        binding.name.visibility = GONE
        binding.tvName.visibility = GONE
        binding.id.visibility = GONE
        binding.tvId.visibility = GONE
        binding.repos.visibility = GONE
        binding.tvRepos.visibility = GONE
    }
}