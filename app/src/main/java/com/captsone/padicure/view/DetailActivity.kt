package com.captsone.padicure.view

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.captsone.padicure.data.ItemData
import com.captsone.padicure.databinding.ActivityDetailBinding
import com.captsone.padicure.view.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras?.getInt("data")
        viewModel.getItemData(data ?: 1)
        viewModel.data.observe(this) {
            setData(it)
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setData(data: ItemData) {
        binding.itemTittle.text = data.name
        Glide.with(binding.root).load(data.photoUrl).into(binding.itemPicture)
        binding.itemTutorial.text = data.tutorial
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}