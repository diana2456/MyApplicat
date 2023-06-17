package com.example.myapplication.ui.fragment.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }


    private fun initView() {
        binding.newAcoynt.setOnClickListener {
            findNavController().navigate(R.id.radisFragment)
        }
    }
}
//подключить бек сохранить на prefer