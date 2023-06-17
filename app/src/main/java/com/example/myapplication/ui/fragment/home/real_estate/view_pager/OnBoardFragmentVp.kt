package com.example.myapplication.ui.fragment.home.real_estate.view_pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.OnboardVpBinding
import com.example.myapplication.ui.fragment.home.real_estate.view_pager.AdapterViewPager.Companion.ON_BOAR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragmentVp : Fragment() {

    private lateinit var binding: OnboardVpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = OnboardVpBinding.inflate(inflater, container, false)
        onBoard()
        return binding.root
    }

    private fun onBoard() {
        arguments.let {val data = it?.getSerializable(ON_BOAR) as Model
        binding.plo.setImageResource(data.img)}

        binding.plo.setOnClickListener {
            findNavController().navigate(R.id.photoFragment)
        }
    }
}