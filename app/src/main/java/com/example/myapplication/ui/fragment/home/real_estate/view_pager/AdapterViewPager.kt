@file:Suppress("DEPRECATION")

package com.example.myapplication.ui.fragment.home.real_estate.view_pager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.R

class AdapterViewPager(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    private val listBoarding = arrayOf(
        Model(R.drawable.image_rv_eiht),
        Model(R.drawable.image_rv_two),
        Model(R.drawable.image_rv_eiht),
        Model(R.drawable.image_rv),)

    override fun getItemCount(): Int = listBoarding.size

    override fun createFragment(position: Int): Fragment {
        val data = bundleOf(ON_BOAR to listBoarding[position])
        val fragment = OnBoardFragmentVp()
        fragment.arguments = data
        return fragment
    }

    companion object{
        const val ON_BOAR = "onBoard"
    }
}