package com.example.myapplication.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.entity.LoadRel
import com.example.myapplication.ui.fragment.home.view_pager.AdapterViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.entity.RealEstate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var handler: Handler
    private val delayTime = 6000L // 5 seconds
    private lateinit var runnable: Runnable
    private val adapterOneLoad = AdapterOneLoad()
    private val adapterTwoLoad = AdapterTwoLoad()
    private val listOneLoad = ArrayList<LoadRel>()
    private val listTwoLoad = ArrayList<LoadRel>()

    private val list = listOf(
        RealEstate(
            image = R.drawable.image_rv_three,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "0",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_rv_four,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "1",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_rv_fiv,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "3",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_rv_six,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "4",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_rv_seven,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "5",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_rv_eiht,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "6",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_nine,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "7",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_ten,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "8",
            status = "0"
        )
    )

    private val listTask = listOf(
        RealEstate(
            image = R.drawable.image_rv,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "9",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_rv_one,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "10",
            status = "0"
        ),
        RealEstate(
            image = R.drawable.image_rv_two,
            tvDil = "Дом",
            tvTitle = "Продается шикарный особняк в европейско...",
            tvSan = "185 000",
            tvKm = "185 м2",
            tvRoom = "4 комнаты",
            tvLocation = "Киргизия 1",
            id = "11",
            status = "0"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


                binding.con.visibility = View.INVISIBLE
                binding.shimmer.startShimmer()

                repeat(10) {
                    listTwoLoad.add(
                        LoadRel(
                            image = R.drawable.screensaver,
                            tvRoom = "",
                            tvLocation = "",
                            tvSan = "",
                            tvKm = "",
                            tvTitle = "",
                            tvDil = "",
                            id = ""
                        )
                    )
                }

                repeat(4) {
                    listOneLoad.add(
                        LoadRel(
                            image = R.drawable.screensaver,
                            tvRoom = "",
                            tvLocation = "",
                            tvSan = "",
                            tvKm = "",
                            tvTitle = "",
                            tvDil = "",
                            id = ""
                        )
                    )
                }

                binding.load.rvOne.adapter = adapterOneLoad
                adapterOneLoad.submitList(listOneLoad)

                binding.load.rvTwo.adapter = adapterTwoLoad
                adapterTwoLoad.submitList(listTwoLoad)

                initView()
                shimmer()
                setupRecyclerViews()
        return binding.root
    }

    private fun setupRecyclerViews() {
        val adapterReal = AdapterRealEstate(requireActivity())
           adapterReal.submitList(list)
        val adapterOne = AdapterOne(requireActivity())
          adapterOne.submitList(listTask)

        binding.iem.rvOne.adapter = adapterOne
        binding.iem.rvTwo.adapter = adapterReal
    }

    private fun shimmer() {
        handler = Handler()
        runnable = Runnable {
            binding.con.visibility = View.VISIBLE
            binding.shimmer.stopShimmer()
            binding.shimmer.visibility = View.INVISIBLE
        }
        handler.postDelayed(runnable, 2000)
    }

    @SuppressLint("MissingInflatedId")
    fun initView() {
        binding.iem.filter.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterViewPager(requireActivity())
        binding.iem.vpv.adapter = adapter
        binding.iem.dotsIndicator.attachTo(binding.iem.vpv)
        handler = Handler()
        runnable = Runnable {
            val currentItem = binding.iem.vpv.currentItem
            val totalItems = binding.iem.vpv.adapter?.itemCount ?: 0
            val nextItem = if (currentItem < totalItems - 1) currentItem + 1 else 0
            binding.iem.vpv.setCurrentItem(nextItem, true)
            handler.postDelayed(runnable, delayTime)
        }
        handler.postDelayed(runnable, delayTime)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}
// филтрация тект виющки обновления изобранные