package com.example.myapplication.ui.fragment.home.photo

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.ui.fragment.home.real_estate.view_pager.AdapterViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPhotoBinding

import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private lateinit var binding : FragmentPhotoBinding
    val adapterViewPager = AdapterViewPager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPhotoBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }

    private fun initView() {
        val adapterViewPager = AdapterViewPager(requireActivity())
        binding.vpv.adapter = adapterViewPager
        binding.ivBlack.setOnClickListener{
            findNavController().navigate(R.id.blankFragment)
        }
        binding.alertTv.setOnClickListener {
                    val view = LayoutInflater.from(requireContext()).inflate(R.layout.diolog_photo, null)
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setView(view)
                    val dialog = builder.create()
                    dialog.show()
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.setCancelable(true)
                    val btn = view.findViewById<MaterialButton>(R.id.btn_tri)
                    btn.setOnClickListener {
                        dialog.dismiss()
                    }
                }
            }

//сделать передачу данных интенты на поделится
}
