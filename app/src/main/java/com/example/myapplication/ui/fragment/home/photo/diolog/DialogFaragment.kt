package com.example.myapplication.ui.fragment.home.photo.diolog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.DiologPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogFaragment : Fragment() {

    private lateinit var binding : DiologPhotoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DiologPhotoBinding.inflate(inflater,container,false)
        return binding.root
    }

}