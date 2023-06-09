package com.example.jjobstarbucks.ui.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.jjobstarbucks.R
import com.example.jjobstarbucks.databinding.FragmentHomeBinding
import com.example.jjobstarbucks.model.Home
import com.example.jjobstarbucks.model.Menu
import com.example.jjobstarbucks.ui.view.MenuView
import com.example.jjobstarbucks.util.readData

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeData = context?.readData("home.json", Home::class.java) ?: return
        val menuData = context?.readData("menu.json", Menu::class.java) ?: return

        val starCount = homeData.user.starCount
        val totalCount = homeData.user.totalCount
        initAppBar(starCount, totalCount, homeData)
        initCoffee(homeData, menuData)
        initBanner(homeData)
        initFood(menuData)
        initFloatingActionButton()
    }

    private fun initFloatingActionButton() {
        binding.sv.setOnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if (scrollY == 0) { // 최상단
                binding.efab.extend() // 확장
            } else {
                binding.efab.shrink() // 수축
            }
        }
    }

    private fun initFood(menuData: Menu) {
        binding.lhcFood.tvTitle.text = getString(R.string.menu_title)
        menuData.food.forEach { menuItem ->
            binding.lhcFood.llMenu.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageView(menuItem.image)
                }
            )
        }
    }

    private fun initBanner(homeData: Home) {
        binding.lbi.ivBanner.apply {
            Glide.with(this).load(homeData.banner.image).into(this)
            this.contentDescription = homeData.banner.contentDescription
        }
    }

    private fun initCoffee(homeData: Home, menuData: Menu) {
        binding.lhcCoffee.tvTitle.text = String.format(getString(R.string.menu_title), homeData.user.nickname)
        menuData.coffee.forEach { menuItem ->
            binding.lhcCoffee.llMenu.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageView(menuItem.image)
                }
            )
        }
    }

    private fun initAppBar(starCount: Int, totalCount: Int, homeData: Home) {
        binding.tvCount.text = String.format(getString(R.string.coupon_count), starCount, totalCount)
        binding.tvAppBar.text = String.format(getString(R.string.home_title), homeData.user.nickname)
        binding.pb.max = totalCount
        Glide.with(binding.ivAppBar).load(homeData.appbarImage).into(binding.ivAppBar)

        ValueAnimator.ofInt(0, homeData.user.starCount).apply {
            duration = 1000
            addUpdateListener {
                binding.pb.progress = it.animatedValue as Int
            }
            start()
        }
    }
}