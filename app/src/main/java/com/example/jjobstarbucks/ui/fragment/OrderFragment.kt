package com.example.jjobstarbucks.ui.fragment

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jjobstarbucks.R
import com.example.jjobstarbucks.databinding.FragmentOrderBinding
import com.example.jjobstarbucks.model.Menu
import com.example.jjobstarbucks.ui.MenuAdapter
import com.example.jjobstarbucks.util.readData
import java.lang.Math.abs

class OrderFragment : Fragment(R.layout.fragment_order) {

    private lateinit var binding: FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)

        val menuData = context?.readData("menu.json", Menu::class.java) ?: return
        val menuAdapter = MenuAdapter().apply {
            submitList(menuData.coffee)
        }
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = menuAdapter
        }

        binding.abl.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val seekPosition = kotlin.math.abs(verticalOffset) / appBarLayout.totalScrollRange.toFloat()
            binding.ml.progress = seekPosition
        }
    }
}