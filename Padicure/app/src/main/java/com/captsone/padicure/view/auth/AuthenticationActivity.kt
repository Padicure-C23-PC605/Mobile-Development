package com.captsone.padicure.view.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.captsone.padicure.R
import com.captsone.padicure.databinding.ActivityAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint

private lateinit var binding: ActivityAuthenticationBinding
@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}