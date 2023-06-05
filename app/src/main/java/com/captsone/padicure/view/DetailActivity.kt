package com.captsone.padicure.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.captsone.padicure.R
import com.captsone.padicure.data.ItemData
import com.captsone.padicure.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras?.getParcelable<ItemData>("data")
        if (data != null) {
            setData(data)
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setData(data: ItemData){
        binding.itemTittle.text = data.name
        Glide.with(binding.root).load(data.photoUrl).into(binding.itemPicture)
        binding.itemTutorial.text = data.tutorial
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}