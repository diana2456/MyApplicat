package com.example.myapplication.ui.fragment.home.real_estate

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRealEstateBinding
import com.example.myapplication.ui.fragment.home.real_estate.view_pager.AdapterViewPager
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class RealEstateFragment : Fragment() {

        private lateinit var handler: Handler
        private lateinit var runnable: Runnable
        private val delayTime = 2000L // 5 seconds
        private lateinit var binding: FragmentRealEstateBinding
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View {
            binding = FragmentRealEstateBinding.inflate(
                inflater,
                container,
                false
            )
            binding.con.visibility = View.INVISIBLE
            binding.shimmer.startShimmer()
            shimmer()
            initView()
            return binding.root
        }

        private fun shimmer() {
            handler = Handler()
            runnable = Runnable {
                binding.con.visibility = View.VISIBLE
                binding.shimmer.stopShimmer()
                binding . shimmer . visibility = View . INVISIBLE
            }
            handler.postDelayed (runnable,delayTime)
        }

        private fun initView() {
            binding.item.bo?.setOnClickListener {
                findNavController().navigate(R.id.navigation_home)
            }
            val adapterViewPager = AdapterViewPager(requireActivity())
            binding.item.vpv.adapter = adapterViewPager
            binding.item.tvDil.setOnClickListener {
                val view =
                    LayoutInflater.from(requireContext()).inflate(R.layout.diolog_photo, null)
                val builder = AlertDialog.Builder(requireContext())
                builder . setView (view)
                val dialog = builder.create()
                dialog . show ()
                dialog.window?.setGravity(Gravity.BOTTOM)
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog . setCancelable (true)
                val btn = view.findViewById<MaterialButton>(R.id.btn_tri)
                btn . setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }
//cделать передачу данных подключить интенты для вызова загрузку чарез ViewModel