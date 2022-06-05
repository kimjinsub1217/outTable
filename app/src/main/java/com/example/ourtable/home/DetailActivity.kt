package com.example.ourtable.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.ourtable.Room.AppDatabase
import com.example.ourtable.databinding.ActivityDetailBinding
import com.example.ourtable.model.Review
import com.example.ourtable.model.Row


class DetailActivity : AppCompatActivity() {


    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "CookDB"
        ).build()

        val model = intent.getParcelableExtra<Row>("cookModel")

        binding.titleTextName.text = model?.rCPNM.orEmpty()
        binding.ingredientTextView.text = model?.rCPPARTSDTLS.orEmpty()
        binding.foodRecipeTextName.text = model?.rCPWAY2.orEmpty()
        binding.foodTypeTextName.text =model?.rCPPAT2.orEmpty()



        recipeTextView(model)
        gliderecipeImageView(model)
        Glide.with(binding.coverImageView.context)
            .load(model?.aTTFILENOMK.orEmpty())
            .into(binding.coverImageView)

        Thread {
            val review = model?.rCPSEQ?.toInt()?.let { db.reviewDao().getOne(it) }
            runOnUiThread {
                binding.reviewEditText.setText(review?.review.orEmpty())
            }
        }.start()


        binding.saveButton.setOnClickListener {
            Thread {
                if (model != null) {
                    db.reviewDao().saveReview(
                        Review(
                            model.rCPSEQ?.toInt() ?: 0,
                            binding.reviewEditText.text.toString()
                        )
                    )
                }
            }.start()
        }
    }

    private fun recipeTextView(model: Row?) {
        binding.recipeTextView1.text=model?.mANUAL01.orEmpty()
        binding.recipeTextView2.text=model?.mANUAL02.orEmpty()
        binding.recipeTextView3.text=model?.mANUAL03.orEmpty()
        binding.recipeTextView4.text=model?.mANUAL04.orEmpty()
        binding.recipeTextView5.text=model?.mANUAL05.orEmpty()
        binding.recipeTextView6.text=model?.mANUAL06.orEmpty()
        binding.recipeTextView7.text=model?.mANUAL07.orEmpty()
        binding.recipeTextView8.text=model?.mANUAL08.orEmpty()
        binding.recipeTextView9.text=model?.mANUAL09.orEmpty()
        binding.recipeTextView10.text=model?.mANUAL10.orEmpty()
        binding.recipeTextView11.text=model?.mANUAL11.orEmpty()
        binding.recipeTextView12.text=model?.mANUAL12.orEmpty()
        binding.recipeTextView13.text=model?.mANUAL13.orEmpty()
        binding.recipeTextView14.text=model?.mANUAL14.orEmpty()
        binding.recipeTextView15.text=model?.mANUAL15.orEmpty()
        binding.recipeTextView16.text=model?.mANUAL16.orEmpty()
        binding.recipeTextView17.text=model?.mANUAL17.orEmpty()
        binding.recipeTextView18.text=model?.mANUAL18.orEmpty()
        binding.recipeTextView19.text=model?.mANUAL19.orEmpty()
        binding.recipeTextView20.text=model?.mANUAL20.orEmpty()
    }

    private fun gliderecipeImageView(model: Row?) {

        Glide.with(binding.recipeImageView1.context)
            .load(model?.mANUALIMG01.orEmpty())
            .into(binding.recipeImageView1)

        Glide.with(binding.recipeImageView2.context)
            .load(model?.mANUALIMG02.orEmpty())
            .into(binding.recipeImageView2)

        Glide.with(binding.recipeImageView3.context)
            .load(model?.mANUALIMG03.orEmpty())
            .into(binding.recipeImageView3)

        Glide.with(binding.recipeImageView4.context)
            .load(model?.mANUALIMG04.orEmpty())
            .into(binding.recipeImageView4)

        Glide.with(binding.recipeImageView5.context)
            .load(model?.mANUALIMG05.orEmpty())
            .into(binding.recipeImageView5)

        Glide.with(binding.recipeImageView6.context)
            .load(model?.mANUALIMG06.orEmpty())
            .into(binding.recipeImageView6)

        Glide.with(binding.recipeImageView7.context)
            .load(model?.mANUALIMG07.orEmpty())
            .into(binding.recipeImageView7)

        Glide.with(binding.recipeImageView8.context)
            .load(model?.mANUALIMG08.orEmpty())
            .into(binding.recipeImageView8)

        Glide.with(binding.recipeImageView9.context)
            .load(model?.mANUALIMG09.orEmpty())
            .into(binding.recipeImageView9)

        Glide.with(binding.recipeImageView10.context)
            .load(model?.mANUALIMG10.orEmpty())
            .into(binding.recipeImageView10)

        Glide.with(binding.recipeImageView11.context)
            .load(model?.mANUALIMG11.orEmpty())
            .into(binding.recipeImageView11)

        Glide.with(binding.recipeImageView12.context)
            .load(model?.mANUALIMG12.orEmpty())
            .into(binding.recipeImageView12)

        Glide.with(binding.recipeImageView13.context)
            .load(model?.mANUALIMG13.orEmpty())
            .into(binding.recipeImageView13)

        Glide.with(binding.recipeImageView14.context)
            .load(model?.mANUALIMG14.orEmpty())
            .into(binding.recipeImageView14)

        Glide.with(binding.recipeImageView15.context)
            .load(model?.mANUALIMG15.orEmpty())
            .into(binding.recipeImageView15)

        Glide.with(binding.recipeImageView16.context)
            .load(model?.mANUALIMG16.orEmpty())
            .into(binding.recipeImageView16)

        Glide.with(binding.recipeImageView17.context)
            .load(model?.mANUALIMG17.orEmpty())
            .into(binding.recipeImageView17)

        Glide.with(binding.recipeImageView18.context)
            .load(model?.mANUALIMG18.orEmpty())
            .into(binding.recipeImageView18)

        Glide.with(binding.recipeImageView19.context)
            .load(model?.mANUALIMG19.orEmpty())
            .into(binding.recipeImageView19)

        Glide.with(binding.recipeImageView20.context)
            .load(model?.mANUALIMG20.orEmpty())
            .into(binding.recipeImageView20)

    }


}