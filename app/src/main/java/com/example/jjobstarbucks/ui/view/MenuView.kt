package com.example.jjobstarbucks.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.jjobstarbucks.R
import com.example.jjobstarbucks.databinding.ItemMenuBinding

class MenuView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    private lateinit var binding: ItemMenuBinding
    private var title: String? = null
    private var imageUrl: String? = null

    init {
        attributeSet?.let {
            initAttr(it)
        }
        initView()
    }

    private fun initAttr(attributeSet: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.MenuView,
            0, 0
        ).apply {
            title = getString(R.styleable.MenuView_title)
            imageUrl = getString(R.styleable.MenuView_imageUrl)
        }
    }

    private fun initView() {
        val view = inflate(context, R.layout.item_menu, this)
        binding = ItemMenuBinding.bind(view)

        title?.let {
            setTitle(it)
        }

        imageUrl?.let {
            setImageView(it)
        }
    }

    fun setTitle(title: String) {
        this.title = title
        binding.tvName.text = title
    }

    fun setImageView(url: String) {
        this.imageUrl = url
        Glide.with(binding.iv).load(url).circleCrop().into(binding.iv)
    }
}