package com.android.hara.presentation.write

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.android.hara.R
import com.android.hara.databinding.ActivityWriteBinding
import com.android.hara.presentation.base.BindingActivity

class WriteActivity : BindingActivity<ActivityWriteBinding>(R.layout.activity_write) {
    // TODO [고민글쓰기] 부분 패키지 추후 네이밍은 변경예정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.write_nav_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.write_nav_graph)
        navController.graph = navGraph
    }
}
