package com.example.myapplication.ui.fragment.about_us

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAboutUsBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AboutUsFragment : Fragment() {

    private lateinit var binding : FragmentAboutUsBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val delayTime = 2000L // 5 seconds

    lateinit var  adapter : Adapter
    private val list = arrayListOf<ModelUs>()
    private val listLoad = arrayListOf<ModelUs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentAboutUsBinding.inflate(inflater,container,false)
        listLoad.add(ModelUs("",R.drawable.screensaver,""))
        listLoad.add(ModelUs("",R.drawable.screensaver,""))
        listLoad.add(ModelUs("",R.drawable.screensaver,""))
        listLoad.add(ModelUs("",R.drawable.screensaver,""))
        listLoad.add(ModelUs("",R.drawable.screensaver,""))
        val  adapterLoad = AdapterLoad()
        binding.con.visibility =View.INVISIBLE
        binding.shimmer.startShimmer()

        adapterLoad.submitList(listLoad)
        binding.load.rvRecyc.adapter = adapterLoad
        shimmer()
        inttView()
        DialogCustom()
        initClickListener()
        return binding.root
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun initClickListener() {
        binding.item.cardIv.setOnClickListener {
            val phoneNumber = "+996703897094"

            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")
            val intent = Intent(Intent.ACTION_VIEW, uri)

            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                requireActivity().startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "На вашем устройстве не отсутсвует это приложение!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.item.cardF.setOnClickListener {
            openInstagramAccount("@dia_na6189")
        }
        binding.item.cardOlo.setOnClickListener {
            openTikTokAccount("@bts_official_bighit")
        }
        binding.item.cardOpo.setOnClickListener {
            makePhoneCall("0703897094")
        }
        binding.item.cardBtn.setOnClickListener {
            val url = "https://chat.openai.com/c/d93d4285-f48f-4fad-aac6-a92d1c422977"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    fun makePhoneCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        val uri = Uri.parse("tel:$phoneNumber")
        intent.data = uri
        startActivity(intent)
    }
    fun openTikTokAccount(username: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val url = "https://www.tiktok.com/@$username"
        val uri = Uri.parse(url)
        intent.data = uri

        if (isTikTokInstalled()) {
            intent.setPackage("com.zhiliaoapp.musically")
        }

        startActivity(intent)
    }

    fun isTikTokInstalled(): Boolean {
        val packageManager = requireActivity().packageManager
        try {
            packageManager.getPackageInfo("com.zhiliaoapp.musically", PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }

    fun openInstagramAccount(username: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val url = "https://www.instagram.com/$username"
        val uri = Uri.parse(url)
        intent.data = uri

        if (isInstagramInstalled()) {
            intent.setPackage("com.instagram.android")
        }

        startActivity(intent)
    }

    private fun isInstagramInstalled(): Boolean {
        val packageManager = requireActivity().packageManager
        return try {
            packageManager.getPackageInfo("com.instagram.android", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }


    private fun shimmer() {
        handler = Handler()
        runnable = Runnable {
            binding.con.visibility = View.VISIBLE
            binding.shimmer.stopShimmer()
            binding.shimmer.visibility = View.INVISIBLE
        }
        handler.postDelayed(runnable, delayTime)
    }
    private fun inttView() {
        list.add(ModelUs("Болот Ибрагимов",R.drawable.stars,"Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet."))
        list.add(ModelUs("Болот Ибрагимов",R.drawable.stars,"Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet."))
        list.add(ModelUs("Болот Ибрагимов",R.drawable.stars,"Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet."))
        list.add(ModelUs("Болот Ибрагимов",R.drawable.stars,"Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet."))
        list.add(ModelUs("Болот Ибрагимов",R.drawable.stars,"Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet."))
        adapter = Adapter()
        adapter.submitList(list)
        binding.item.rvRecyc.adapter = adapter
    }

    private fun DialogCustom() {
        binding.item.btn.setOnClickListener {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.diolog_about_us, null)
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            val btn = view.findViewById<MaterialButton>(R.id.btn_)
            btn.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}
//интенты готовы