package com.example.ourtable


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ourtable.databinding.ActivityMainBinding
import com.example.ourtable.home.HomeFragment
import com.example.ourtable.my_page.MyPageFragment
import com.example.ourtable.recipe.RecipeFragment
import com.example.ourtable.store.StoreFragment
import com.example.ourtable.style.StyleFragment
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val home = HomeFragment()
        val myPage = MyPageFragment()
        val recipe = RecipeFragment()
        val store = StoreFragment()
        val style = StyleFragment()

        replaceFragment(home)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(home)
                R.id.my_page -> replaceFragment(myPage)
                R.id.recipe -> replaceFragment(recipe)
                R.id.store -> replaceFragment(store)
                R.id.style -> replaceFragment(style)

            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }

}