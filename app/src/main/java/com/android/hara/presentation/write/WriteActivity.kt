package com.android.hara.presentation.write

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.android.hara.R
import com.android.hara.databinding.ActivityWriteBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.util.makeSnackbar
import com.android.hara.presentation.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteActivity : BindingActivity<ActivityWriteBinding>(R.layout.activity_write) {
    // TODO [고민글쓰기] 부분 패키지 추후 네이밍은 변경예정
    private val writeViewModel by viewModels<WriteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
//        setViewModel() -> 뷰모델 사용하면 애니메이션이 안 됨
        setNavigation()
        setProgress()
    }
//    private fun setViewModel() {
//        binding.writeViewModel = writeViewModel
//    }

    private fun init() {
        binding.ibWriteCloseButton.setOnSingleClickListener {
            finish()
        }

        writeViewModel.success.observe(this) {
            // 성공하면 꺼지고 실패하면 메세지
            if (it) finish()
            else binding.root.makeSnackbar("서버통신에 실패하였어요!")
        }
    }

    private fun setProgress() {
        writeViewModel.progress.observe(this) {
            binding.sbWriteSeekbar.setProgress(writeViewModel.progress.value!!, true)
        }
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.write_nav_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.write_nav_graph)
        navController.graph = navGraph
    }
}
