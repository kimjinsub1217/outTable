package com.example.ourtable.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourtable.BuildConfig
import com.example.ourtable.adapter.CookAdapter
import com.example.ourtable.api.CookService
import com.example.ourtable.databinding.ActivitySearchBinding
import com.example.ourtable.model.Cook
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: CookAdapter
    private lateinit var cookService: CookService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initCookRecyclerView()


        val client:OkHttpClient =OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
//                    DEBUG일때만 BODY까지지  보여줌
                    level =if(BuildConfig.DEBUG){
                        HttpLoggingInterceptor.Level.BODY
                    }else{
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            ).build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        cookService = retrofit.create(CookService::class.java)
        cookService.getCookByName()
            .enqueue(object : Callback<Cook> {
                override fun onFailure(call: Call<Cook>, t: Throwable) {
                    Toast.makeText(this@SearchActivity, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(call: Call<Cook>, response: Response<Cook>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
                        Log.d(TAG, it.toString())

                        it.cOOKRCP01?.row?.forEach { rows ->
                            Log.d(TAG, rows.rCPNM.toString())

                        }


                        adapter.submitList(it.cOOKRCP01?.row)
                    }

                }

            })


    }

    private fun initCookRecyclerView() {
        adapter = CookAdapter(itemClickedListener = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("cookModel", it)
            startActivity(intent)
        })

        binding.cookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cookRecyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val BASE_URL = "https://openapi.foodsafetykorea.go.kr/"
    }
}