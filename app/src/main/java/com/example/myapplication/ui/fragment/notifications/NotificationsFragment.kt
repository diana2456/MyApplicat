package com.example.myapplication.ui.fragment.notifications

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.example.myapplication.entity.Favorite
import com.example.myapplication.entity.LoadRel
import com.example.myapplication.room.FavDB
import com.example.myapplication.ui.fragment.home.AdapterTwoLoad
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class NotificationsFragment: Fragment(){
    private lateinit var binding : FragmentNotificationsBinding
    lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favDB: FavDB
    private var favItemList: MutableList<Favorite> = mutableListOf()
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val delayTime = 2000L


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.con.visibility = View.INVISIBLE
        binding.shimmer.startShimmer()

        val listLoad: ArrayList<LoadRel> = arrayListOf()
        repeat(8) {
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
        }
        val adapterLoad = AdapterTwoLoad()
        adapterLoad.submitList(listLoad)
        binding.load.rv.adapter = adapterLoad
        shimmer()
        favDB = FavDB(requireActivity())
        loadData()
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.item.rvH)
        initView()
        return binding.root
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

    private fun initView() {
        binding.item.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }
    }

    @SuppressLint("Range")
    private fun loadData() {
        favItemList.clear()
        val db = favDB.readableDatabase
        val cursor = favDB.selectAllFavoriteList()
        try {
            while (cursor.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_TITLE))
                val id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID))
                val image = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_IMAGE)).toInt()
                val dil = cursor.getString(cursor.getColumnIndex(FavDB.DIL))
                val room = cursor.getString(cursor.getColumnIndex(FavDB.ROOM))
                val san = cursor.getString(cursor.getColumnIndex(FavDB.SAN))
                val local = cursor.getString(cursor.getColumnIndex(FavDB.LOCAL))
                val km = cursor.getString(cursor.getColumnIndex(FavDB.KM))
                val favItem = Favorite(id, image, dil, title, san, km, room, local)
                favItemList.add(favItem)
            }
        } finally {
            cursor.close()
            db?.close()
        }
        favoriteAdapter = FavoriteAdapter(requireActivity(), favItemList)
        binding.item.rvH.adapter = favoriteAdapter
    }

    private val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val favItem = favItemList[position]
            if (direction == ItemTouchHelper.LEFT) {
                favItemList.removeAt(position)
                favoriteAdapter.submitList(favItemList.toMutableList())
                favoriteAdapter.notifyItemRemoved(position)
                favoriteAdapter.notifyItemRangeChanged(position, favoriteAdapter.itemCount)
                favDB.removeFav(favItem.id)
            }
        }
    }
}