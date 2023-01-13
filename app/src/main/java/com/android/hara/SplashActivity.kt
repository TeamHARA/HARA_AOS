package com.android.hara

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.hara.presentation.home.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, HomeActivity::class.java)
        lifecycleScope.launch {
            delay(1500)
            startActivity(intent)
            overridePendingTransition(0, 0)
            finish()
        }
    }
}
