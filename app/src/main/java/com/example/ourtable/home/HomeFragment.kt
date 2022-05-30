package com.example.ourtable.home



import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourtable.adapter.CookAdapter
import com.example.ourtable.R
import com.example.ourtable.api.CookService
import com.example.ourtable.databinding.FragmentHomeBinding
import com.example.ourtable.model.Cook

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CookAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        initCookRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CookService::class.java)
        service.getMoviesByName()
            .enqueue(object : Callback<Cook> {
                override fun onFailure(call: Call<Cook>, t: Throwable) {
                    Toast.makeText(activity, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_LONG).show()
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
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun initCookRecyclerView() {
        adapter = CookAdapter()

        binding.cookRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.cookRecyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val BASE_URL = "https://openapi.foodsafetykorea.go.kr/"
    }


}

