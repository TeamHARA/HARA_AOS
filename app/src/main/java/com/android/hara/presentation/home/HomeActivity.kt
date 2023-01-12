package com.android.hara.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.hara.R
import com.android.hara.databinding.ActivityHomeBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.decision.FinalDecideActivity
import com.android.hara.presentation.home.fragment.together.TogetherFragment
import com.android.hara.presentation.home.viewmodel.HomeViewModel
import com.android.hara.presentation.search.SearchActivity
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.WriteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    // TODO 바텀 네비게이션 및 [함께해라], [보관함] 프래그먼트가 들어갈 HomeAcitity

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
        setListener()
    }

    private fun setListener() {
        binding.fabHome.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
        homeViewModel

        //TEST FINAL
        binding.ivHomeSetting.setOnSingleClickListener {
            startActivity(Intent(this, FinalDecideActivity::class.java))
        }
        binding.ivHomeSearch.setOnSingleClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        binding.bottomNavHome.setOnItemReselectedListener {
            if (it.itemId == R.id.fragment_together) {
                TogetherFragment.setScroll()
                return@setOnItemReselectedListener
            }
            return@setOnItemReselectedListener
        }
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.bottom_nav_graph)
        navController.graph = navGraph
        binding.bottomNavHome.setupWithNavController(navController)
        binding.bottomNavHome.menu.findItem(R.id.fragment_write).isEnabled = false
        // 가운데는 플로팅 버튼이 있으므로 메뉴는 비활성화
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_together -> {
                    binding.ivHomeNoti.visibility = View.GONE
                    binding.ivHomeSearch.visibility = View.VISIBLE
                }
                R.id.fragment_storage -> {
                    binding.ivHomeNoti.visibility = View.VISIBLE
                    binding.ivHomeSearch.visibility = View.GONE
                }
                else -> throw IllegalAccessException("Error.NavController")
            }
        }
    }
}
