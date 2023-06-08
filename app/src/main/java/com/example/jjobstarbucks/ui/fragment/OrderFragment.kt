package com.example.jjobstarbucks.ui.fragment

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjobstarbucks.R
import com.example.jjobstarbucks.databinding.FragmentOrderBinding

class OrderFragment : Fragment(R.layout.fragment_order) {

    private lateinit var binding: FragmentOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentOrderBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }


}