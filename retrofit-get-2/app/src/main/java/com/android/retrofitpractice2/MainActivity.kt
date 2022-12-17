package com.android.retrofitpractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.retrofitpractice2.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnOk.setOnClickListener {
                UserService().getUserById(etId.text.toString(), GetUserCallback())
            }
        }
    }

    inner class GetUserCallback: RetrofitCallback<User> {
        override fun onSuccess(code: Int, responseData: User) {
            binding.apply {
                responseData.let {
                    if(responseData.userId != "") {
                        showInfoViews()
                        Glide.with(this@MainActivity).load(it.image).override(150, 150).into(ivImage)
                        tvName.text = it.name
                        tvId.text = it.userId
                        tvRepos.text = it.repos.toString()
                    } else {
                        notShowInfoViews()
                        Toast.makeText(this@MainActivity, "입력하신 ID는 존재하지 않습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        override fun onError(t: Throwable) {
            Log.d("레트로핏 에러", t.message ?: "유저 정보 불러오는 중 통신오류")
        }

        override fun onFailure(code: Int) {
            Log.d("레트로핏 에러", "onResponse: Error Code $code")
        }
    }

    private fun showInfoViews() {
        binding.apply {
            ivImage.visibility = View.VISIBLE
            name.visibility = View.VISIBLE
            tvName.visibility = View.VISIBLE
            id.visibility = View.VISIBLE
            tvId.visibility = View.VISIBLE
            repos.visibility = View.VISIBLE
            tvRepos.visibility = View.VISIBLE
        }
    }

    private fun notShowInfoViews() {
        binding.apply {
            ivImage.visibility = View.GONE
            name.visibility = View.GONE
            tvName.visibility = View.GONE
            id.visibility = View.GONE
            tvId.visibility = View.GONE
            repos.visibility = View.GONE
            tvRepos.visibility = View.GONE
        }
    }
}