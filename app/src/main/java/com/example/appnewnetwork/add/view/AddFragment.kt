package com.example.appnewnetwork.add.view

import android.system.Os.bind
import com.example.appnewnetwork.R
import com.example.appnewnetwork.add.Add
import com.example.appnewnetwork.common.base.BaseFragment
import com.example.appnewnetwork.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : BaseFragment<FragmentAddBinding, Add.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind
), Add.View {

    override lateinit var presenter: Add.Presenter
    override fun setupViews() {
        val tabLayout = binding?.addTab
        val viewPager = binding?.addViewpager
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
               tab.text = getString(adapter.tabs[position])
            }.attach()
        }

    }

    override fun setUpPresenter() {
    }

}