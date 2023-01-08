package com.android.hara.presentation.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.hara.R
import com.android.hara.databinding.ActivitySettingBinding
import com.android.hara.presentation.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    //TODO [설정]부분 입니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}