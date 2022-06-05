package com.example.ourtable.home


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.example.ourtable.BuildConfig
import com.example.ourtable.R
import com.example.ourtable.Room.AppDatabase
import com.example.ourtable.adapter.BannerListAdapter
import com.example.ourtable.adapter.CookAdapter
import com.example.ourtable.api.CookService
import com.example.ourtable.databinding.FragmentHomeBinding
import com.example.ourtable.model.COOKRCP01
import com.example.ourtable.model.Cook
import com.example.ourtable.model.Row
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewPagerAdapter =BannerListAdapter(itemClickedListener = {
        val intent=Intent(activity,MainDetailActivity::class.java)
        intent.putExtra("cookModel2",it)
        startActivity(intent)
    })

    private lateinit var cookService: CookService

    private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
    private val MIN_ALPHA = 0.5f // 어두워지는 정도를 나타낸 듯 하다.



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.bannerViewPager2.adapter = viewPagerAdapter


        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.bannerViewPager2.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.bannerViewPager2.offscreenPageLimit = 1
//        binding.bannerViewPager2.setPageTransformer(ZoomOutPageTransformer()) // 애니메이션 적용

        var myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.viePage2TextView.text = "무얼 먹을지 고민이라면"
                binding.viePage2TextView2.text = "${viewPagerAdapter.currentList[position].rCPNM.toString()}\n어떠세요?"
            }
        }

        binding.bannerViewPager2.registerOnPageChangeCallback(myPageChangeCallback)

        val client: OkHttpClient = OkHttpClient.Builder()
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
            .baseUrl(HomeFragment.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        cookService = retrofit.create(CookService::class.java)
        cookService.getCookBanner()
            .enqueue(object : Callback<Cook> {
                override fun onFailure(call: Call<Cook>, t: Throwable) {

                }

                override fun onResponse(call: Call<Cook>, response: Response<Cook>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
//                        Log.d(HomeFragment.TAG, it.toString())
                        viewPagerAdapter.submitList(it.cOOKRCP01?.row)
                    }

                }

            })



        binding.search.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }




        return binding.root
    }

    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }

//    private fun initCookRecyclerView() {
//        adapter = CookAdapter(itemClickedListener = {
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("cookModel", it)
//            startActivity(intent)
//        })

    companion object {
        private const val TAG = "HomeFragment"
        private const val BASE_URL = "https://openapi.foodsafetykorea.go.kr/"
    }



}

