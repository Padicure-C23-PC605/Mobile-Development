package com.captsone.padicure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import com.captsone.padicure.databinding.ActivityMainBinding
import com.captsone.padicure.view.HomeActivity
import com.captsone.padicure.view.auth.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var  repository: Repository
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            when (val response = repository.isLogin()) {
                is Response.IsSuccessful<*> -> {
                    if (!(response.data as Boolean)) {
                        binding.startButton.setOnClickListener {
                            Intent(this@MainActivity, AuthenticationActivity::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        }
                    }
                    else {
                        Intent(this@MainActivity, HomeActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                }
                else -> {}
            }
        }

    }
}