package com.android.sjsockettest

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.sjsockettest.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSend.setOnClickListener {
                SocketAsyncTask().execute()
            }
        }
    }

    inner class SocketAsyncTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
            try {
                // 첫번째 인자 ip 주소, 두번째 인자 port 번호
                val socket = Socket("", 0)

                // 서버로부 받은 문자열 출력
                val input = socket.getInputStream()
                val dataInputStream = DataInputStream(input)
                val stringData = dataInputStream.readUTF()
                runOnUiThread {
                    binding.tvServerString.text = stringData
                }

                // edittext에 작성한 문자열 서버에 보내기
                val output = socket.getOutputStream()
                val dataOutputStream = DataOutputStream(output)
                dataOutputStream.writeUTF(binding.etString.text.toString())

                socket.close()

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }
    }
}