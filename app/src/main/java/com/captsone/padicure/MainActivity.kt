package com.captsone.padicure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.captsone.padicure.data.Repository
import com.captsone.padicure.data.Response
import com.captsone.padicure.view.HomeActivity
import com.captsone.padicure.view.auth.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var  repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            when (val response = repository.isLogin()) {
                is Response.IsSuccessful<*> -> {
                    if (!(response.data as Boolean)) {
                        Intent(this@MainActivity, AuthenticationActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                    else {
                        Intent(this@MainActivity, HomeActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                    finish()
                }
                else -> {}
            }
        }

    }
}