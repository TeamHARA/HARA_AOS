package com.android.hara.presentation.home

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.hara.R
import com.android.hara.databinding.ActivityHomeBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.custom.PickerBottomSheetDialog

class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    // TODO 바텀 네비게이션 및 [함께해라], [보관함] 프래그먼트가 들어갈 HomeAcitity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()

        binding.fabHome.setOnClickListener {
//            val intent = Intent(this, WriteActivity::class.java)
//            startActivity(intent)
            //TODO 나중에 되돌리기
            PickerBottomSheetDialog().show(supportFragmentManager, "asdf")
        }
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.bottom_nav_graph)
        navController.graph = navGraph
        binding.bottomNavHome.setupWithNavController(navController)
    }
}
