package com.example.myapplication.ui.fragment.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.entity.LoadRel
import com.example.myapplication.entity.RealEstate
import com.example.myapplication.ui.fragment.home.AdapterRealEstate
import com.example.myapplication.ui.fragment.home.AdapterTwoLoad
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {


    private lateinit var binding : FragmentDashboardBinding
    private val list: ArrayList<RealEstate> = arrayListOf()
    private val listLoad: ArrayList<LoadRel> = arrayListOf()
    private lateinit var adapterRealEstate : AdapterRealEstate
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val delayTime = 2000L


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        binding.con.visibility =View.INVISIBLE
        binding.shimmer.startShimmer()
        listLoad.add(
            LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
        id = "")
        )
        listLoad.add(
            LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
            id = ""
        )
        )
        listLoad.add(
            LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
            id = ""
        )
        )
        listLoad.add(
            LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
            id = ""
        )
        )
        val adapterLoad = AdapterTwoLoad()
        adapterLoad.submitList(listLoad)
        binding.load.rvRecom.adapter = adapterLoad
        initView()
        shimmer()
        recycler()
        search()
        return binding.root
    }

    private fun search() {

    }

    private fun initView() {
        list.add(
            RealEstate(
            image = R.drawable.image_rv_six,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvLocation = "Киргизия 1",
            tvRoom = "",
            status = "",
            id = ""
        )
        )
        list.add(
            RealEstate(
            image = R.drawable.image_iv_,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvLocation = "Киргизия 1",
            tvRoom = "",
            status = "",
            id = ""
        )
        )
        list.add(
            RealEstate(
            image = R.drawable.image_nine,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvLocation = "Киргизия 1",
            tvRoom = "",
            id = "",
            status = ""
        )
        )
        list.add(
            RealEstate(
            image = R.drawable.image_der,
            tvDil = "Квартира",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvLocation = "Киргизия 1",
            tvRoom = "",
            status = "",
            id = ""
        )
        )
        adapterRealEstate = AdapterRealEstate(requireActivity())
        adapterRealEstate.submitList(list)
        binding.item.rvRecom.adapter = adapterRealEstate
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

    fun recycler(){
        val SCROLL_THRESHOLD = 200

        binding.item.rvRecom.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItemPosition <= SCROLL_THRESHOLD) {
                    binding.item.tvSearch.ivSearch.visibility = View.VISIBLE
                } else {
                    binding.item.tvSearch.ivSearch.visibility = View.GONE
                }
            }
        })

    }


}