package com.android.hara.presentation.home.fragment.storage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.onesec.OneSecActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    // FinalDecideFragment에서 버튼을 다스릴 애가 local임.
    // local이 data binding으로 연결된 fragment_final_decide.xml에 있는 selected라는 변수로 전달됨.
    // .xml에서 selected가 버튼에 달려있어.
    // .xml에서 selected가 app.어쩌고에 연결돼있어.
    // util/BindingConversion.kt에 보면 app. 어쩌고라고 써있었음.
    // 그 .kt 파일에 보면 sel이 있어.
    // sel에 최종적으로 selected가 전달되는 것
    // [결론] Fragment의 local = .xml의 selected = BindingConversion.kt의 sel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2, TabLayout 참조
        val viewPager: ViewPager2 = binding.viewpager
        val tabLayout: TabLayout = binding.tlStorageTab

        // FragmentStateAdapter 생성
        val viewpagerFragmentAdapter = ViewpagerFragmentAdapter(requireActivity())

        // ViewPager2의 adapter 설정
        viewPager.adapter = viewpagerFragmentAdapter

        // ###### TabLayout과 ViewPager2를 연결
        // 1. 탭메뉴의 이름을 리스트로 생성해둔다.
        val tabTitles = listOf<String>("혼자 고민", "함께 고민")

        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정한다.
        TabLayoutMediator(tabLayout, viewPager, {tab, position -> tab.text = tabTitles[position]}).attach()

        // 버튼 클릭 시 '1초만에 해결하기' 액티비티로 연결
        binding.cvBtnOnesec.setOnClickListener {
            val intent = Intent(activity, OneSecActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // 이거 지워도 되나..
            startActivity(intent)
        }

    }
}