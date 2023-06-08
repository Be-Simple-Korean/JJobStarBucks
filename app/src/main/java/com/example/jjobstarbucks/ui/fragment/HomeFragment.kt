package com.example.jjobstarbucks.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.jjobstarbucks.R
import com.example.jjobstarbucks.databinding.FragmentHomeBinding
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

        val homeData = context?.readData() ?: return
        val starCount = homeData.user.starCount
        val totalCount = homeData.user.totalCount
        binding.tvCount.text = String.format(getString(R.string.coupon_count), starCount, totalCount)
        binding.tvAppBar.text = String.format(getString(R.string.home_title), homeData.user.nickname)
        binding.pb.max = totalCount
        binding.pb.progress = starCount
        Glide.with(binding.ivAppBar).load(homeData.appbarImage).into(binding.ivAppBar)


        for(i in 0..3){
            binding.lhc.llMenu.addView(
                MenuView(context = requireContext()).apply {
                    setTitle("아이스 아메리카노")
                    setImageView("https://picsum.photos/200/100")
                }
            )
        }

    }

}