package com.example.myapplication.ui.fragment.home.view_pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentOnBoardBinding
import com.example.myapplication.ui.fragment.home.real_estate.view_pager.Model
import com.example.myapplication.ui.fragment.home.view_pager.AdapterViewPager.Companion.ON_BOARD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment : Fragment(){

    private lateinit var binding : FragmentOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false)
        onBoard()
        return binding.root
    }

    private fun onBoard() {
        arguments.let {val data = it?.getSerializable(ON_BOARD) as Model
        binding.ivPhoto.setImageResource(data.img)}
    }


}