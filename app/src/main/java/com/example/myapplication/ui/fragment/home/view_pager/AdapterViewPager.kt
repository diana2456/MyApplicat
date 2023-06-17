package com.example.myapplication.ui.fragment.home.view_pager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.R
import com.example.myapplication.ui.fragment.home.real_estate.view_pager.Model


class AdapterViewPager(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    private val listBoarding = arrayOf(
        Model(R.drawable.pager_one),
        Model(R.drawable.pager_two),
        Model(R.drawable.pager_three),
        Model(R.drawable.pager_four),)

    override fun getItemCount(): Int = listBoarding.size

    override fun createFragment(position: Int): Fragment {
        val data = bundleOf(ON_BOARD to listBoarding[position])
        val fragment = OnBoardFragment()
        fragment.arguments = data
        return fragment
    }

    companion object{
        const val ON_BOARD = "onBoard"
    }
}
//pacширить фотки