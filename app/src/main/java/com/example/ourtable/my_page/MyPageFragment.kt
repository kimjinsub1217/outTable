package com.example.ourtable.my_page

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ourtable.LoginActivity
import com.example.ourtable.MainActivity
import com.example.ourtable.R
import com.example.ourtable.databinding.FragmentMyPageBinding
import com.kakao.sdk.user.UserApiClient


class MyPageFragment : Fragment(R.layout.fragment_my_page) {
    lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMyPageBinding.bind(view)
        mainActivity = context as MainActivity

        UserApiClient.instance.me { user, _ ->

            binding.name.text = "${user?.kakaoAccount?.profile?.nickname}"

            val imageUrl = user?.kakaoAccount?.profile?.profileImageUrl
            val defaultImage = R.drawable.profile

            Glide.with(this)
                .load(imageUrl) // 불러올 이미지 url
                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .circleCrop() // 동그랗게 자르기
                .into(binding.image) // 이미지를 넣을 뷰
        }

//        binding.kakaoLogoutButton.setOnClickListener {
//            UserApiClient.instance.logout { error ->
//                if (error != null) {
//                    Toast.makeText(mainActivity, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(mainActivity, "로그아웃 성공", Toast.LENGTH_SHORT).show()
//                }
//                val intent = Intent(activity, LoginActivity::class.java)
//                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
//            }
//        }
//
//        binding.kakaoUnlinkButton.setOnClickListener {
//            UserApiClient.instance.unlink { error ->
//                if (error != null) {
//                    Toast.makeText(mainActivity, "회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(mainActivity, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(activity, LoginActivity::class.java)
//                    startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
//                }
//            }
//        }

        binding.settings.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
    }


}

