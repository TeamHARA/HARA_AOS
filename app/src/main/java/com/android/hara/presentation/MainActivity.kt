package com.android.hara.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hara.BuildConfig
import com.android.hara.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.e(BuildConfig.BASE_URL)
    }
}