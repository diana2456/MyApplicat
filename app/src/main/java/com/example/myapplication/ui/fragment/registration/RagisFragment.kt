package com.example.myapplication.ui.fragment.registration

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRagisBinding


@Suppress("KotlinConstantConditions")
class RagisFragment : Fragment() {

    private lateinit var binding : FragmentRagisBinding
    private var registrationListener: OnRegistrationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegistrationListener) {
            registrationListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRagisBinding.inflate(inflater,container,false)
        initView()
        edit()
        return binding.root
    }

    private fun edit() {
        binding.namberPasword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                registrationListener?.onRegistrationEditTextChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun initView() {
        binding.voite.setOnClickListener {
                findNavController().navigate(R.id.navigation_home)
        }
    }

    override fun onDetach() {
        super.onDetach()
        registrationListener = null
    }
}